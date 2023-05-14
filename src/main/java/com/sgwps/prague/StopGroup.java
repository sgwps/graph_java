package com.sgwps.prague;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.locationtech.jts.geom.Coordinate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class StopGroup {
    
    @AllArgsConstructor
    @Getter
    public static class Stop {
        @JsonIgnoreProperties({ "z", "valid", "m" })
        Coordinate[] coordinate;
        String name;
    }

    int id;

    Stop[] stops;


    
}
