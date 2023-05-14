package com.sgwps.NYCsubway;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import org.locationtech.jts.geom.Coordinate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Line {
    ArrayList<Station> stations;
    String name;


    public Line(ArrayList<Station> stations,  String name) {
        this.stations = stations;
        this.name = name;
    }

    private Entry<Station, Station> Find2Mins(double[] array) {
        int index1 = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0) {
                if (index1 == -1 || array[index1] > array[i]) index1 = i;
            }
        }

        int index2 = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0 && i != index1) {
                if (index2 == -1 || array[index2] > array[i]) index2 = i;
            }
        }

        return new AbstractMap.SimpleEntry<Station,Station>(stations.get(index1), stations.get(index2));
    }


    public void FindClostest(Station station){
        double[] distances = new double[stations.size()];
        for (int i = 0; i < distances.length; i++) {
            distances[i] = station.coordinate.distance(stations.get(i).coordinate);
        }
        station.closestByLines.putIfAbsent(name, Find2Mins(distances));
    }


    public Entry<Station, Station> findTermine() {
        for (Station i : stations) {
            FindClostest(i);
        }
        for (Station i : stations) {
            Station station1 = i.closestByLines.get(name).getKey();
            Station station2 = i.closestByLines.get(name).getValue();

            Station station1_1 = station1.closestByLines.get(name).getKey();
            Station station1_2 = station1.closestByLines.get(name).getValue();


            Station station2_1 = station2.closestByLines.get(name).getKey();
            Station station2_2 = station2.closestByLines.get(name).getValue();

            if (station1.equals(station2_1) || station1.equals(station2_2)) {
                return new AbstractMap.SimpleEntry<Station, Station>(i, station1);
            }
            if (station2.equals(station1_1) || station2.equals(station1_2)) {
                return new AbstractMap.SimpleEntry<Station, Station>(i, station2);
            }
        }
        return null;
    }


    public ArrayList<Station> build(){
        Entry<Station, Station> start = findTermine();
        ArrayList<Station> result = new ArrayList<Station>(stations.size());
        result.add(start.getKey());
        result.add(start.getValue());
        for (int i = 2; i < stations.size(); i++) {
            System.out.println(result.get(i - 1).name);
            Station current = result.get(i - 1);
            Station st1 = current.closestByLines.get(name).getKey();
            Station st2 = current.closestByLines.get(name).getValue();
            if (st1.equals(result.get(i - 2))) {
                result.add(st2);
            }
            else if (st2.equals(result.get(i - 2))) {
                result.add(st1);
            }
        }
        return result;
    }

    ArrayList<Station> ordered;

    public void ReadJson() throws FileNotFoundException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode lineA = mapper.readTree(new FileInputStream("lineA.json"));

        ordered = new ArrayList<Station>();
        for (JsonNode i : lineA) {
            Coordinate c = new Coordinate(i.get(0).asDouble(), i.get(1).asDouble());
            Station min = stations.get(0);
            double minDST = c.distance(min.coordinate);
            for (Station station : stations.subList(1, stations.size())) {
                double dst = c.distance(station.coordinate);
                if (dst < minDST) {
                    minDST = dst;
                    min = station;
                }
            }
            ordered.add(min);
        }
        for (Station i : ordered) {
            System.out.println(i.name);
        }

    }
    
}
