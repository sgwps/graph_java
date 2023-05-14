package com.sgwps.prague;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import java.util.stream.Collectors;
import java.util.stream.Stream;


import org.locationtech.jts.geom.Coordinate;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.sgwps.graph.vertex.Indexable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/* 

//@AllArgsConstructor
//@NoArgsConstructor
public class Station2 { //implements Indexable {
/* 





    @AllArgsConstructor
    @NoArgsConstructor
    static class Id {
        @JsonProperty("id")
        String id;

        @JsonProperty("node")
        int node;

        @Override
        public boolean equals(Object obj) {
            Id other = (Id) obj;
            return node == other.node && id.equals(other.id);
        }
    }

    @JsonProperty("name")
    String name;

    @JsonIgnoreProperties({ "z", "valid", "m" })
    public Coordinate[] coordinate;

    @JsonProperty("id")
    public Id id;

    @Override
    @JsonIgnore
    public int getIndex() {
        return id.hashCode();
    }

    public static void selectTramStops() throws FileNotFoundException, IOException {




        ObjectMapper mapper = new ObjectMapper();

        ArrayList<Station> stations = new ArrayList<>();
        FileReader file = new FileReader("stops.csv");
        BufferedReader reader = new BufferedReader(file);
        reader.readLine();
        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            String[] splitted = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
            // csv line example: U1Z2P,"Boletická",50.133011,14.513584,"P",,0,,0,,B,1,2
            Coordinate coordinate = new Coordinate(Double.parseDouble(splitted[2]), Double.parseDouble(splitted[3]));
            Station station = new Station(splitted[1], new Coordinate[] { coordinate }, normalizedID(splitted[0]));
            stations.add(station);
        }
        //return stations;

        /* 
        HashMap<String, HashSet<Station> > stations = new HashMap<>(); 
        FileReader file = new FileReader("stops.csv");
        BufferedReader reader = new BufferedReader(file);
        reader.readLine();
        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            String[] splitted = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
            // csv line example: U1Z2P,"Boletická",50.133011,14.513584,"P",,0,,0,,B,1,2
            Coordinate coordinate = new Coordinate(Double.parseDouble(splitted[2]), Double.parseDouble(splitted[3]));
            Station station = new Station(splitted[1], new Coordinate[] {coordinate}, splitted[0]);
            stations.putIfAbsent(station.name, new HashSet<>());
            stations.get(station.name).add(station);
        }
        return stations;
        */
        /*JsonNode node = mapper.readTree(new FileInputStream("stopsRAW.json")).get("stopGroups");
        for (JsonNode station : node) {
            for (JsonNode stop : station.get("stops")) {
                boolean isTram = false;
                for (JsonNode line : stop.get("lines")) {
                    if (line.get("type").asText().equals("tram"))
                        isTram = true;
                }
                if (isTram) {
                    Coordinate[] coordinates = new Coordinate[] {
                            new Coordinate(stop.get("lat").asDouble(), stop.get("lon").asDouble()) };
                    String name = station.get("name").asText();
                    String id = normalizedID(stop.get("gtfsIds").get(0).asText());
                    stations.add(new Station(name, coordinates, new Id(normalizedID(id), station.get("node").asInt())));
                }
            }

        }
        return stations;

    }

    public static String normalizedID(String prevId) {
        Pattern pattern = Pattern.compile("\\w\\d*\\w");
        Matcher matcher = pattern.matcher(prevId);

        matcher.find();
        return matcher.group(0);
    }

    public static void createJSON() throws FileNotFoundException, IOException {

        ArrayList<Station> stations = getHashMapfromCSV();
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode array = JsonNodeFactory.instance.arrayNode();
        for (Station station : stations) {
            array.add(mapper.valueToTree(station));
        }
        mapper.writeValue(new FileOutputStream("stops.json"), array);
        //ObjectMapper mapper = new ObjectMapper();
        //ArrayNode array = JsonNodeFactory.instance.arrayNode();
        /*HashMap<String, HashSet<Station> > stations = getHashMapfromCSV();
         * // предположение: ID станций с разными названиями уникальны до второй буквы -
         * неверно
         * for (Entry<String, HashSet<Station>> stationEntry : stations.entrySet()) {
         * //final String normalizedId =
         * normalizedID(stationSet.stream().findAny().get().id);
         * Map<String, List<Station>> grouped =
         * stationEntry.getValue().stream().collect(Collectors.groupingBy(station ->
         * normalizedID(station.id)));
         * for (Entry<String, List<Station>> stationEntry2 : grouped.entrySet()) {
         * String id = stationEntry2.getKey();
         * String name = stationEntry.getKey();
         * List<Coordinate[]> coordinatesList =
        stationEntry.getValue().stream().map(station ->
         * station.coordinate).collect(Collectors.toList());
         * List<Coordinate> coordinates = new ArrayList<Coordinate>();
         * coordinatesList.stream().forEach(arr -> Arrays.stream(arr).forEach(i ->
         * coordinates.add(i)));
         * Coordinate[] coordinates2 = new Coordinate[coordinates.size()];
         * coordinates.toArray(coordinates2);
         * Station newStation = new Station(name, coordinates2, id);
         * 
         * array.add(mapper.valueToTree(newStation));
         * }
         * }
         * mapper.writeValue(new FileOutputStream("stops.json"), array);
         */

    //}


    // достаточно обойтись только id (а возможно он равен node)
    /*private static boolean areUnique() throws FileNotFoundException, IOException {
        Map<Integer, String> id_s = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode tree = mapper.readTree(new FileInputStream("stops.json"));
        for (JsonNode jsonNode : tree) {
            Id id = new Id(jsonNode.get("id").get("id").asText(), jsonNode.get("id").get("node").asInt());
            if (id_s.containsKey(id.node)) {
                if (! (id_s.get(id.node).equals(id.id))) {
                    System.out.println(jsonNode.get("name"));
                    System.out.println(id_s.get(id.node));
                    System.out.print("->");
                    System.out.print(!id_s.get(id.node).equals(id.id));
                    System.out.println(id.id);
               // return false;
                }
            }
            else {
                id_s.put(id.node, id.id);
            }
        }
        return true;
    }


    private static int getId(String id) {
        Pattern pattern = Pattern.compile("U\\d*Z");
        Pattern pattern2 = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(id);
        if (! matcher.matches()) {
            System.out.print(id);
        }
        matcher = pattern2.matcher(id);
        matcher.find();
        return Integer.parseInt(matcher.group(0));
    } 


    private static boolean areIdsAndNodesEqual() throws FileNotFoundException, IOException {
        Map<Integer, String> id_s = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode tree = mapper.readTree(new FileInputStream("stops.json"));
        for (JsonNode jsonNode : tree) {
            Id id = new Id(jsonNode.get("id").get("id").asText(), jsonNode.get("id").get("node").asInt());
            if (getId(id.id) != id.node) {
                System.out.println(id.id);
            }
        }
        return true;
    }


    private static String getName(ArrayList<Station> stations) {
        String result = stations.stream().findAny().get().name;
        boolean flag = stations.stream().allMatch(station -> station.name.equals(result));
        if (!flag) {
            System.out.println(result);
        }
        return result;
    }

    private static void finalJson() throws FileNotFoundException, IOException{
        Map<Integer, ArrayList<Station>> id_s = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode tree = mapper.readTree(new FileInputStream("stops.json"));
        for (JsonNode stationNode : tree) {
            Station station = mapper.convertValue(stationNode, Station.class);
            id_s.putIfAbsent(station.id.node, new ArrayList<>());
            id_s.get(station.id.node).add(station);
        }
        ArrayList<StopGroup> stationsFinal = new ArrayList<>();
        for (Entry<Integer, ArrayList<Station>> stationEntry : id_s.entrySet()) {
             
            Map<String, List<Station>> map = stationEntry.getValue().stream().collect(Collectors.groupingBy(station -> station.name));
            ArrayList<StopGroup.Stop> stops = new ArrayList<>();
            for (Entry<String, List<Station>> stopEntry : map.entrySet()) {
                boolean coordLen = stopEntry.getValue().stream().allMatch(i -> i.coordinate.length == 1);
                if (!coordLen) {
                    System.out.println(stopEntry.getKey());
                }
                List<Coordinate> coordinateList = stopEntry.getValue().stream().map(i -> i.coordinate[0]).collect(Collectors.toList());            
                Coordinate[] coordinates = new Coordinate[coordinateList.size()];
                coordinateList.toArray(coordinates);
                // остановкам в узлах нужен id
                stops.add(new StopGroup.Stop(coordinates, stopEntry.getKey()));

            }
            StopGroup.Stop[] stops1 = new StopGroup.Stop[stops.size()];
            stops.toArray(stops1);

            stationsFinal.add(new StopGroup(stationEntry.getKey(), stops1));
        }
        mapper.writeValue(new FileOutputStream("stopsFinal.json"), stationsFinal);

    }



    public static void main(String[] args) throws FileNotFoundException, IOException {
        //createJSON();
        //areUnique();
        finalJson();
    }
}
*/