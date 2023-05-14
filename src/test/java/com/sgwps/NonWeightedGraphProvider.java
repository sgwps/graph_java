package com.sgwps;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import com.sgwps.nonWeightedTestCase.AllCasesCreator;
import com.sgwps.nonWeightedTestCase.NonWeightedGraphTestInfoDemoArray;
import com.sgwps.nonWeightedTestCase.NonWeightedGraphTestInfoDemoMap;
import com.sgwps.nonWeightedTestCase.NonWeightedGraphTestInfoDemoMatrix;
import com.sgwps.nonWeightedTestCase.TestCaseStreamFactory;

public class NonWeightedGraphProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        Stream<? extends Arguments> stream1 = TestCaseStreamFactory.getArgumentStream(AllCasesCreator.case1());
       // Stream<? extends Arguments> result = Stream.concat(stream1, stream1);
        return stream1;
    }

}
