package com.sgwps.prague;

import java.io.IOException;

import org.locationtech.jts.geom.Coordinate;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class CoordinateDeserealizer extends StdDeserializer<Coordinate>{
    public CoordinateDeserealizer() { 
        this(null); 
    } 

    public CoordinateDeserealizer(Class<Coordinate> vc) { 
        super(vc); 
    }

    @Override
    public Coordinate deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonNode node = parser.getCodec().readTree(parser);
        Coordinate result = new Coordinate(node.get("lat").asDouble(), node.get("lon").asDouble());
        return result;
    }


}
