package com.sgwps.prague;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class StopIdSerealizer extends StdSerializer<StopId> {

    public StopIdSerealizer() {
        this(null);
    }
  
    public StopIdSerealizer(Class<StopId> c) {
        super(c);
    }

    @Override
    public void serialize(StopId value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("node", value.node);
        gen.writeNumberField("platform", value.platform);
        gen.writeEndObject();
    }
    
}