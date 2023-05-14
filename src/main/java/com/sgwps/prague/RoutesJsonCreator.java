package com.sgwps.prague;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

public class RoutesJsonCreator {
    static ObjectMapper mapper = new ObjectMapper();

    static public ArrayNode parseRoutesFromCSV() throws IOException {
        FileReader file = new FileReader("routes.csv");
        BufferedReader reader = new BufferedReader(file);
        reader.readLine();
        // route_id 0,agency_id 1,route_short_name 2,route_long_name 3,route_type
        // 4,route_url 5,route_color 6,route_text_color 7,is_night
        // 8,is_regional,is_substitute_transport
        // L991,99,A,"Nemocnice Motol - Petřiny - Skalka - Depo
        // Hostivař",1,"https://pid.cz/linka/A",00A562,FFFFFF,0,0,0

        ArrayNode result = JsonNodeFactory.instance.arrayNode();
        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            String[] splitted = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
            int routeType = Integer.parseInt(splitted[4]);
            int isNight = Integer.parseInt(splitted[8]);
            int isSubstitute = Integer.parseInt(splitted[10]);
            if (routeType == 0 && isNight == 0 && isSubstitute == 0) {
                Route route = new Route(Integer.parseInt(splitted[2]), splitted[0]);
                result.add(mapper.valueToTree(route));
            }
        }
        return result;

    }

    public static ArrayList<String> routesIdList() throws FileNotFoundException, IOException {
        JsonNode node = mapper.readTree(new FileInputStream("routes.json"));
        ArrayList<String> result = new ArrayList<>();
        for (JsonNode route : node) {
            result.add(route.get("id").asText());
        }
        return result;
    }

    public static int createFileWithTramTripsOnly() throws FileNotFoundException, IOException {
        ArrayList<String> ids = routesIdList();
        FileReader file = new FileReader("trips.csv");
        BufferedReader reader = new BufferedReader(file);
        FileWriter result = new FileWriter("selectedTrips.csv", false);
        result.append(reader.readLine());
        result.append('\n');
        result.flush();
        int c = 0;
        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            String[] splitted = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
            if (ids.contains(splitted[0])) {
                result.append(line);
                result.append('\n');
                result.flush();
                c++;
            }
        }
        return c;
    }

    public static void main(String[] args)
            throws StreamWriteException, DatabindException, FileNotFoundException, IOException {
        System.out.println(createFileWithTramTripsOnly());

    }
}
