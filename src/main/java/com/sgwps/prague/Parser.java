package com.sgwps.prague;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Parser {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode result = (ObjectNode) mapper.readTree(new FileInputStream("prague.json"));
        for (int number = 1; number <= 26; number++) {

            if (result.get(Integer.toString(number)).get("routes").size() == 0) {
                System.out.println(number);
                URL url = new URL(String.format(
                        "https://transit.land/api/v2/rest/routes/r-u2fkb-%d?api_key=6NhYyY2b0MbGM7czB1G1m0su2j9Lx9mj",
                        number));
                System.out.println(url.toString());
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                JsonNode node = mapper.readTree(url.openStream());
                result.set(Integer.toString(number), node);
            }
        }
        mapper.writeValue(new FileOutputStream("prague.json"), result);
    }
}
