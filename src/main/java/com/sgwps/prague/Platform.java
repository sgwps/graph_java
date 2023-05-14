package com.sgwps.prague;

import org.locationtech.jts.geom.Coordinate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Platform {
    int id;
    String name;
    Coordinate coordinate;
}
