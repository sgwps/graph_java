package com.sgwps;

import java.util.ArrayList;
import java.util.List;

import com.sgwps.graph.edge.BaseEdge;
import com.sgwps.graph.vertex.VertexBase;

public class TestRunner {
    VertexBase[] vertexs = {
        new VertexBase(0),
        new VertexBase(1),
        new VertexBase(2),
        new VertexBase(3),
        new VertexBase(4),
    };


    ArrayList<BaseEdge>  edges =  new ArrayList<BaseEdge> (
        List.of(
        new BaseEdge(vertexs[0], vertexs[1]),
        new BaseEdge(vertexs[1], vertexs[0]),

        new BaseEdge(vertexs[0], vertexs[2]),
        new BaseEdge(vertexs[2], vertexs[0]),

        new BaseEdge(vertexs[2], vertexs[4]),
        new BaseEdge(vertexs[4], vertexs[2]),

        new BaseEdge(vertexs[4], vertexs[3]),
        new BaseEdge(vertexs[3], vertexs[4]),

        new BaseEdge(vertexs[3], vertexs[1]),        
        new BaseEdge(vertexs[1], vertexs[3])        
    ));
    // метод reverse в ребро, вернуть ориентируемость
    // в bfs - массив всех возможных вершин

}
