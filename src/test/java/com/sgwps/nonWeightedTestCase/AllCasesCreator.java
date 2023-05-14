package com.sgwps.nonWeightedTestCase;

import java.util.ArrayList;
import java.util.List;

import com.sgwps.graph.edge.BaseEdge;
import com.sgwps.graph.vertex.VertexBase;

public class AllCasesCreator {
    public static NonWeightedGraphTestCaseBase case1() {
        ArrayList<VertexBase> vertexs = new ArrayList<VertexBase>(
                List.of(
                        new VertexBase(0),
                        new VertexBase(1),
                        new VertexBase(2),
                        new VertexBase(3),
                        new VertexBase(4))

        );
        return new NonWeightedGraphTestCaseBase(
            vertexs,

                new ArrayList<BaseEdge>(
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
                                new BaseEdge(vertexs.get(1), vertexs.get(3)))),
                // метод reverse в ребро, вернуть ориентируемость
                // в bfs - массив всех возможных вершин

                new int[] { 2, 2, 2, 2, 2 },

                new int[][][] {
                        { new int[] { 0 }, new int[] { 0 }, new int[] { 0 }, new int[] { 1 }, new int[] { 2 } },
                        { new int[] { 1 }, new int[] { 1 }, new int[] { 0 }, new int[] { 1 }, new int[] { 3 } },
                        { new int[] { 2 }, new int[] { 0 }, new int[] { 2 }, new int[] { 4 }, new int[] { 2 } },
                        { new int[] { 1 }, new int[] { 3 }, new int[] { 4 }, new int[] { 3 }, new int[] { 3 } },
                        { new int[] { 2 }, new int[] { 3 }, new int[] { 4 }, new int[] { 4 }, new int[] { 4 } }
                },

                new int[][] {
                        { 0, 1, 1, 2, 2 },
                        { 1, 0, 2, 1, 2 },
                        { 1, 2, 0, 2, 1 },
                        { 2, 1, 2, 0, 1 },
                        { 2, 2, 1, 1, 0 }
                },

                1);
    }
}
