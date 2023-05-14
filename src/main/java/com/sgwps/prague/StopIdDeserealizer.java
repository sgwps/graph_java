package com.sgwps.prague;

import java.io.IOException;

import org.locationtech.jts.geom.Coordinate;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class StopIdDeserealizer extends StdDeserializer<StopId>{
    public StopIdDeserealizer() { 
        this(null); 
    } 

    public StopIdDeserealizer(Class<StopId> vc) { 
        super(vc); 
    }

    @Override
    public StopId deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonNode node = parser.getCodec().readTree(parser);
        StopId result = new StopId(node.get("node").asInt(), node.get("platform").asInt());
        return result;
    }


}
