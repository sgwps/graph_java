package com.sgwps.prague;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StopId {
    int node;
    int platform;
    static Pattern originRegEx = Pattern.compile("^(U\\d+Z\\d+)");
    static Pattern numberRegEx = Pattern.compile("\\d+");

    static StopId parseId(String origin) {
        if (!CheckId(origin)) {
            throw new IllegalArgumentException("Invalid id");
        }
        Matcher matcher = numberRegEx.matcher(origin);
        matcher.find();
        String nodeString = matcher.group();
        matcher.find();
        String platformString = matcher.group();
        return new StopId(Integer.parseInt(nodeString), Integer.parseInt(platformString));

    }

    static boolean CheckId(String origin) {
        Matcher matcher = originRegEx.matcher(origin);
        return matcher.find();
    }
}
