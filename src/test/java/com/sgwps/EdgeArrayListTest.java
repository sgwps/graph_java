package com.sgwps;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameter;

import com.sgwps.graph.collections.EdgeArrayList;
import com.sgwps.graph.collections.VertexArrayList;
import com.sgwps.graph.edge.BaseEdge;
import com.sgwps.graph.graph.Graph;
import com.sgwps.graph.graph.base.NonWeightedGraphBase;
import com.sgwps.graph.vertex.Indexable;
import com.sgwps.graph.vertex.VertexBase;
import com.sgwps.nonWeightedTestCase.NonWeightedGraphTestCaseBase;

/**
 * Unit test for simple App.
 */
public class EdgeArrayListTest {

    @ParameterizedTest()
    @ArgumentsSource(NonWeightedGraphProvider.class)
    public void VertexDegreesInt(NonWeightedGraphTestCaseBase graph) {
        int[] result = new int[5];
        for (int i = 0; i < graph.getGraph().getVertexes().size(); i++) {
            result[i] = graph.getGraph().getVertexDegree(i);
        }
        assertArrayEquals(result, graph.getVertexDegrees());
    }

    @ParameterizedTest()
    @ArgumentsSource(NonWeightedGraphProvider.class)
    public void VertexDegreesIndexable(NonWeightedGraphTestCaseBase graph) {
        int[] result = new int[5];
        for (int i = 0; i < graph.getGraph().getVertexes().size(); i++) {
            result[i] = graph.getGraph().getVertexDegree(graph.getGraph().getVertexes().getVertexByIndex(i));
        }
        assertArrayEquals(result, graph.getVertexDegrees());
    }

    @ParameterizedTest()
    @ArgumentsSource(NonWeightedGraphProvider.class)
    public void GetComponentCountTest(NonWeightedGraphTestCaseBase graph) {
        assertEquals(graph.getComponentCount(), graph.getComponentCount());
    }

    @ParameterizedTest()
    @ArgumentsSource(NonWeightedGraphProvider.class)
    public void ShortestRouteByPreviousTest(NonWeightedGraphTestCaseBase graph) {

        int[][][] expected = graph.getShortestRoutesIndexes();
        for (Indexable i : graph.getGraph().getVertexes()) {
            ArrayList<Indexable> result = graph.getGraph()
                    .ShotestRouteByPrevious(graph.getGraph().getVertexes().getVertexByIndex(i.getIndex()));
            boolean finalResult = true;
            for (int j = 0; j < result.size(); j++) {
                final int j1 = result.get(j).getIndex();
                int[] expectedArr2 = expected[i.getIndex()][j];
                finalResult = finalResult
                        && Arrays.stream(expectedArr2).anyMatch(x -> x == j1);
                if (!finalResult) {
                    System.out.print("я кретин");
                }
            }

            assertTrue(finalResult);
        }

    }

    @ParameterizedTest()
    @ArgumentsSource(NonWeightedGraphProvider.class)
    public void ShortestRouteTest(NonWeightedGraphTestCaseBase graph) {
        int[][] expected = graph.getShortestRoutesLength();
        for (Indexable i : graph.getGraph().getVertexes()) {
            ArrayList<Integer> result = graph.getGraph()
                    .ShortestRoutes(graph.getGraph().getVertexes().getVertexByIndex(i.getIndex()));
            int[] arr = result.stream().mapToInt(j -> j).toArray();
            assertArrayEquals(expected[i.getIndex()], arr);
        }

    }

}
