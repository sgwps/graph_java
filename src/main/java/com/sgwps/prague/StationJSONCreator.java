package com.sgwps.prague;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.locationtech.jts.geom.Coordinate;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class StationJSONCreator {
    static ObjectMapper mapper = new ObjectMapper();
    static SimpleModule module = new SimpleModule();

    static {
        module.addDeserializer(Coordinate.class, new CoordinateDeserealizer());
        module.addSerializer(Coordinate.class, new CoordinateSerealizer());
        module.addSerializer(StopId.class, new StopIdSerealizer());
        module.addDeserializer(StopId.class, new StopIdDeserealizer());
        mapper.registerModule(module);
    }

    private static boolean isTram(JsonNode stop) {
        boolean result = false;
        for (JsonNode line : stop.get("lines")) {
            if (line.get("type").asText().equals("tram"))
                result = true;
        }
        return result;
    }

    private static JsonNode createJsonNode(String name, int node, Coordinate coordinate, String id) {
        ObjectNode result = JsonNodeFactory.instance.objectNode();
        JsonNode coordinateNode = mapper.valueToTree(coordinate);
        result.putIfAbsent("coordinate", coordinateNode);
        result.putIfAbsent("name", mapper.valueToTree(name));
        result.putIfAbsent("node", mapper.valueToTree(node));
        result.putIfAbsent("id", mapper.valueToTree(id));
        return result;
    }

    public static ArrayNode selectTramStops() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode stationsRaw = mapper.readTree(new FileInputStream("stopsRAW.json")).get("stopGroups");
        ArrayNode result = JsonNodeFactory.instance.arrayNode();
        for (JsonNode station : stationsRaw) {
            String name = station.get("name").asText();
            int node = station.get("node").asInt();
            for (JsonNode stop : station.get("stops")) {
                if (isTram(stop)) {
                    Coordinate coordinate = new Coordinate(stop.get("lat").asDouble(), stop.get("lon").asDouble());
                    String id = stop.get("gtfsIds").get(0).asText();
                    result.add(createJsonNode(name, node, coordinate, id));
                }
            }

        }
        return result;
    }


    public static ArrayNode changedId() throws FileNotFoundException, IOException{
        Map<Integer, Map<Integer, Boolean>> checker = new HashMap<>();
        JsonNode stations = mapper.readTree(new FileInputStream("stops2.json"));
        ArrayNode result = JsonNodeFactory.instance.arrayNode();
        for (JsonNode station : stations) {
            StopId id = StopId.parseId(station.get("id").asText());
            checker.putIfAbsent(id.node, new HashMap<>());
            checker.get(id.node).putIfAbsent(id.platform, false);
            if (checker.get(id.node).get(id.platform)) {
                throw new IllegalArgumentException("Id_s not unique");
            }
            JsonNode coordinateNode = station.get("coordinate");
            JsonNode nameNode = station.get("name");
            JsonNode idNode = mapper.valueToTree(id);
            ObjectNode newStationNode = JsonNodeFactory.instance.objectNode();
            newStationNode.putIfAbsent("coordinate", coordinateNode);
            newStationNode.putIfAbsent("name", nameNode);
            newStationNode.putIfAbsent("id", idNode);
            result.add(newStationNode);
        }
        return result;

    }

    public static ArrayNode finalJson() throws FileNotFoundException, IOException{
        JsonNode stations = mapper.readTree(new FileInputStream("stops3.json"));
        Map<Integer, List<Platform>> checker = new HashMap<>();
        for (JsonNode station : stations) {
            String name = station.get("name").asText();
            Coordinate coordinate = mapper.treeToValue(station.get("coordinate"), Coordinate.class);
            StopId id = mapper.treeToValue(station.get("id"), StopId.class);
            checker.putIfAbsent(id.node, new ArrayList<>());
            checker.get(id.node).add(new Platform(id.platform, name, coordinate));
        }
        ArrayNode result = JsonNodeFactory.instance.arrayNode();
        for (Entry<Integer, List<Platform>> entry : checker.entrySet()) {
            JsonNode platformList = mapper.valueToTree(entry.getValue());
            ObjectNode stationNode = JsonNodeFactory.instance.objectNode();
            stationNode.putIfAbsent("platforms", platformList);
            stationNode.putIfAbsent("id", mapper.valueToTree(entry.getKey()));
            result.add(stationNode);
        }
        return result;
    }


    public static void main(String[] args) throws StreamWriteException, DatabindException, FileNotFoundException, IOException {
        mapper.writeValue(new FileOutputStream("stops.json"), finalJson());
    }

}