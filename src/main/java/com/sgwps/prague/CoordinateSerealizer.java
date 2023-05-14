package com.sgwps.prague;

import java.io.IOException;

import org.locationtech.jts.geom.Coordinate;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CoordinateSerealizer extends StdSerializer<Coordinate> {

    public CoordinateSerealizer() {
        this(null);
    }
  
    public CoordinateSerealizer(Class<Coordinate> c) {
        super(c);
    }

    @Override
    public void serialize(Coordinate value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("lat", value.x);
        gen.writeNumberField("lon", value.y);
        gen.writeEndObject();
    }
    
}
