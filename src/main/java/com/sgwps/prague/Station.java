package com.sgwps.prague;

import lombok.Getter;

@Getter
public class Station {
    int id;
    Platform[] platforms;

    public Station(int id, Platform... platforms) {
        this.id = id;
        this.platforms = platforms;
    }


}
