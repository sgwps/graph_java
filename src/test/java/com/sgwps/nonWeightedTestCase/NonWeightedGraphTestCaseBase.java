package com.sgwps.nonWeightedTestCase;

import java.util.ArrayList;
import java.util.List;

import com.sgwps.graph.edge.BaseEdge;
import com.sgwps.graph.graph.NonWeightedGraph;
import com.sgwps.graph.vertex.VertexBase;

import lombok.Getter;

public class NonWeightedGraphTestCaseBase {

    ArrayList<VertexBase> vertexs;

    ArrayList<BaseEdge> edges;
    // метод reverse в ребро, вернуть ориентируемость
    // в bfs - массив всех возможных вершин

    @Getter
    NonWeightedGraph graph;

    @Getter
    int[] vertexDegrees;

    @Getter
    int[][][] ShortestRoutesIndexes;

    @Getter
    int[][] ShortestRoutesLength;

    @Getter
    int componentCount;

    public NonWeightedGraphTestCaseBase(ArrayList<VertexBase> vertexs, ArrayList<BaseEdge> edges, int[] vertexDegrees, int[][][] ShortestRoutesIndexes, int[][] ShortestRoutesLength, int componentCount) {
        this.vertexs = vertexs;
        this.edges = edges;
        this.vertexDegrees = vertexDegrees;
        this.ShortestRoutesIndexes = ShortestRoutesIndexes;
        this.ShortestRoutesLength = ShortestRoutesLength;
        this.componentCount = componentCount;
    }

    


    public NonWeightedGraphTestCaseBase(){
        vertexs = new ArrayList<VertexBase>(
                List.of(
                        new VertexBase(0),
                        new VertexBase(1),
                        new VertexBase(2),
                        new VertexBase(3),
                        new VertexBase(4))

        );

        edges = new ArrayList<BaseEdge>(
                List.of(
                        new BaseEdge(vertexs.get(0), vertexs.get(1)),
                        new BaseEdge(vertexs.get(1), vertexs.get(0)),

                        new BaseEdge(vertexs.get(0), vertexs.get(2)),
                        new BaseEdge(vertexs.get(2), vertexs.get(0)),

                        new BaseEdge(vertexs.get(2), vertexs.get(4)),
                        new BaseEdge(vertexs.get(4), vertexs.get(2)),

                        new BaseEdge(vertexs.get(4), vertexs.get(3)),
                        new BaseEdge(vertexs.get(3), vertexs.get(4)),

                        new BaseEdge(vertexs.get(3), vertexs.get(1)),
                        new BaseEdge(vertexs.get(1), vertexs.get(3))));
        // метод reverse в ребро, вернуть ориентируемость
        // в bfs - массив всех возможных вершин

        
        vertexDegrees = new int[] { 2, 2, 2, 2, 2 };

        ShortestRoutesIndexes = new int[][][] {
                { new int[] { 0 }, new int[] { 0 }, new int[] { 0 }, new int[] { 1 }, new int[] { 2 } },
                { new int[] { 1 }, new int[] { 1 }, new int[] { 0 }, new int[] { 1 }, new int[] { 3 } },
                { new int[] { 2 }, new int[] { 0 }, new int[] { 2 }, new int[] { 4 }, new int[] { 2 } },
                { new int[] { 1 }, new int[] { 3 }, new int[] { 4 }, new int[] { 3 }, new int[] { 3 } },
                { new int[] { 2 }, new int[] { 3 }, new int[] { 4 }, new int[] { 4 }, new int[] { 4 } }
        };

        ShortestRoutesLength = new int[][] {
                { 0, 1, 1, 2, 2 },
                { 1, 0, 2, 1, 2 },
                { 1, 2, 0, 2, 1 },
                { 2, 1, 2, 0, 1 },
                { 2, 2, 1, 1, 0 }
        };

        componentCount = 1;
    }
}
