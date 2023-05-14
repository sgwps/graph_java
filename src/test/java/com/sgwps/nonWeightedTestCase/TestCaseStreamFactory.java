package com.sgwps.nonWeightedTestCase;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import com.sgwps.graph.graph.base.NonWeightedGraphBase;

public class TestCaseStreamFactory {
    public static Stream<? extends Arguments> getArgumentStream(NonWeightedGraphTestCaseBase base) {
        return Stream.of(
        Arguments.of(new NonWeightedGraphTestInfoDemoArray(base)),
        Arguments.of(new NonWeightedGraphTestInfoDemoMatrix(base)),
        Arguments.of(new NonWeightedGraphTestInfoDemoMap(base))
       );
    }
}
