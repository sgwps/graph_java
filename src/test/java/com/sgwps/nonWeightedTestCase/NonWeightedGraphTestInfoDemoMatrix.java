package com.sgwps.nonWeightedTestCase;

import java.util.ArrayList;

import com.sgwps.graph.collections.EdgeArrayList;
import com.sgwps.graph.collections.MatrixEdgeCollection;
import com.sgwps.graph.collections.VertexArrayList;
import com.sgwps.graph.edge.BaseEdge;
import com.sgwps.graph.graph.base.NonWeightedGraphBase;
import com.sgwps.graph.vertex.VertexBase;

public class NonWeightedGraphTestInfoDemoMatrix extends NonWeightedGraphTestCaseBase {
    public NonWeightedGraphTestInfoDemoMatrix(){
        graph = new NonWeightedGraphBase<BaseEdge>(new VertexArrayList<>(vertexs), new MatrixEdgeCollection<BaseEdge>(edges, vertexs.size() - 1));
    }


    public NonWeightedGraphTestInfoDemoMatrix(ArrayList<VertexBase> vertexs, ArrayList<BaseEdge> edges,
            int[] vertexDegrees, int[][][] ShortestRoutesIndexes, int[][] ShortestRoutesLength, int componentCount) {
        super(vertexs, edges, vertexDegrees, ShortestRoutesIndexes, ShortestRoutesLength, componentCount);
        graph = new NonWeightedGraphBase<BaseEdge>(new VertexArrayList<>(vertexs), new MatrixEdgeCollection<BaseEdge>(edges, vertexs.size() - 1));
    }
    
    public NonWeightedGraphTestInfoDemoMatrix(NonWeightedGraphTestCaseBase base) {
        super(base.vertexs, base.edges, base.vertexDegrees, base.ShortestRoutesIndexes, base.ShortestRoutesLength, base.componentCount);
        graph = new NonWeightedGraphBase<BaseEdge>(new VertexArrayList<>(vertexs), new MatrixEdgeCollection<BaseEdge>(edges, vertexs.size() - 1));

    }
    
}
