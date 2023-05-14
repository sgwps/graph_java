package com.sgwps.prague;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.NotImplementedException;
import org.locationtech.jts.geom.Coordinate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Line {
    public static void newJson() throws FileNotFoundException, IOException{
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(new FileInputStream("prague.json"));
        ObjectNode result = JsonNodeFactory.instance.objectNode();
        RoutesIterator routes = new RoutesIterator();
        for (String route : routes) {
            JsonNode routeNode = node.get(route).get("routes").get(0).get("route_stops");
            List<String> terminate = Arrays.asList(node.get(route).get("routes").get(0).get("route_long_name").asText().split(" - "));
            int i = 0;

            if (Integer.parseInt(route) == 24) {
                routeNode = node.get(route).get("routes").get(1).get("route_stops");
                terminate = Arrays.asList(node.get(route).get("routes").get(1).get("route_long_name").asText().split(" - "));
    
            for (JsonNode a : routeNode) {
                System.out.println(a.get("stop").get("stop_name").asText());
            }
        }
            while (!terminate.contains(routeNode.get(i).get("stop").get("stop_name").asText())) i++;
            ArrayNode route1 = JsonNodeFactory.instance.arrayNode();
            ArrayNode route2 = JsonNodeFactory.instance.arrayNode();
            route1.add(routeNode.get(i).get("stop").get("id"));
            i++;
            while (!terminate.contains(routeNode.get(i).get("stop").get("stop_name").asText())){
                 route1.add(routeNode.get(i).get("id"));
                i++;
            }
            route1.add(routeNode.get(i++).get("stop").get("id"));
            route2.add(routeNode.get(i++).get("stop").get("id"));
            while (i < routeNode.size() && !terminate.contains(routeNode.get(i).get("stop").get("stop_name").asText())) route2.add(routeNode.get(i++).get("stop").get("id"));
            route2.add(routeNode.get(i++).get("stop").get("id"));
            ArrayNode routeFinal = JsonNodeFactory.instance.arrayNode();
            routeFinal.add(route1);
            routeFinal.add(route2);
            result.putIfAbsent(route, routeFinal);
        }
        mapper.writeValue(new FileOutputStream("routes.json"), result);

    }


    public static void main(String[] args) throws FileNotFoundException, IOException {
        newJson();

    }
}
