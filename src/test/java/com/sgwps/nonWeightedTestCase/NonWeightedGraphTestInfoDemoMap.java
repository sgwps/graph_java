package com.sgwps.nonWeightedTestCase;

import com.sgwps.graph.collections.MatrixEdgeCollection;
import com.sgwps.graph.collections.VertexArrayList;
import com.sgwps.graph.edge.BaseEdge;
import com.sgwps.graph.graph.base.NonWeightedGraphBase;
import com.sgwps.graph.vertex.VertexBase;

import java.util.ArrayList;

import com.sgwps.graph.collections.MapEdgeCollection;

public class NonWeightedGraphTestInfoDemoMap extends NonWeightedGraphTestCaseBase {
    public NonWeightedGraphTestInfoDemoMap(){
        graph = new NonWeightedGraphBase<BaseEdge>(new VertexArrayList<VertexBase>(vertexs), new MapEdgeCollection<BaseEdge>(edges));
    }


    public NonWeightedGraphTestInfoDemoMap(ArrayList<VertexBase> vertexs, ArrayList<BaseEdge> edges, int[] vertexDegrees, int[][][] ShortestRoutesIndexes, int[][] ShortestRoutesLength, int componentCount) {
        super(vertexs, edges, vertexDegrees, ShortestRoutesIndexes, ShortestRoutesLength, componentCount);
        graph = new NonWeightedGraphBase<BaseEdge>(new VertexArrayList<VertexBase>(vertexs), new MapEdgeCollection<BaseEdge>(edges));
    }


    public NonWeightedGraphTestInfoDemoMap(NonWeightedGraphTestCaseBase base) {
        super(base.vertexs, base.edges, base.vertexDegrees, base.ShortestRoutesIndexes, base.ShortestRoutesLength, base.componentCount);
        graph = new NonWeightedGraphBase<BaseEdge>(new VertexArrayList<VertexBase>(vertexs), new MapEdgeCollection<BaseEdge>(edges));

    }
}
