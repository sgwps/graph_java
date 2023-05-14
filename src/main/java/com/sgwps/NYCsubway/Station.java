package com.sgwps.NYCsubway;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;


import org.locationtech.jts.geom.Coordinate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Station {
    Coordinate coordinate;
    String name;
    String[] lines;
    static HashMap<String, ArrayList<Station>> allLines = new HashMap<String, ArrayList<Station>>();
    HashMap<String, Entry<Station, Station>> closestByLines = new HashMap<String, Entry<Station, Station>>();




    public Station(JsonNode node) {
        name = node.get("properties").get("name").asText();
        lines = node.get("properties").get("line").asText().split("-");
        double c1 = node.get("geometry").get("coordinates").get(0).asDouble();
        double c2 = node.get("geometry").get("coordinates").get(1).asDouble();
        coordinate = new Coordinate(c1, c2);
        for (String i : lines) {
            allLines.putIfAbsent(i, new ArrayList<Station>());
            allLines.get(i).add(this);
        }
    }



    public static void main(String args[]) throws FileNotFoundException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(new FileInputStream("stations.geojson")).get("features");
        ArrayList<Station> stations = new ArrayList<Station>();
        for (JsonNode node : jsonNode) {
            stations.add(new Station(node));
        }
        HashMap<String, ArrayList<Station>> allLines2 = allLines;
        Line A = new Line( allLines.get("A"), "A");

        A.ReadJson();

    }
    
}
