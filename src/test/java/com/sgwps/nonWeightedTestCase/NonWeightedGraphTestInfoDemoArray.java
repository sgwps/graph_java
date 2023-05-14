package com.sgwps.nonWeightedTestCase;

import java.util.ArrayList;

import com.sgwps.graph.collections.EdgeArrayList;
import com.sgwps.graph.collections.VertexArrayList;
import com.sgwps.graph.edge.BaseEdge;
import com.sgwps.graph.graph.base.NonWeightedGraphBase;
import com.sgwps.graph.vertex.VertexBase;

public class NonWeightedGraphTestInfoDemoArray extends NonWeightedGraphTestCaseBase {
    public NonWeightedGraphTestInfoDemoArray() {
        graph = new NonWeightedGraphBase<>(new VertexArrayList<>(vertexs), new EdgeArrayList<>(edges));
    }

    public NonWeightedGraphTestInfoDemoArray(ArrayList<VertexBase> vertexs, ArrayList<BaseEdge> edges,
            int[] vertexDegrees, int[][][] ShortestRoutesIndexes, int[][] ShortestRoutesLength, int componentCount) {
        super(vertexs, edges, vertexDegrees, ShortestRoutesIndexes, ShortestRoutesLength, componentCount);
        graph = new NonWeightedGraphBase<>(new VertexArrayList<>(vertexs), new EdgeArrayList<>(edges));
    }


    public NonWeightedGraphTestInfoDemoArray(NonWeightedGraphTestCaseBase base) {
        super(base.vertexs, base.edges, base.vertexDegrees, base.ShortestRoutesIndexes, base.ShortestRoutesLength, base.componentCount);
        graph = new NonWeightedGraphBase<>(new VertexArrayList<>(vertexs), new EdgeArrayList<>(edges));

    }
}
