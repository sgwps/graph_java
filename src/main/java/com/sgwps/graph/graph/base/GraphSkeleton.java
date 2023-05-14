package com.sgwps.graph.graph.base;

import com.sgwps.graph.collections.VertexCollection;
import com.sgwps.graph.edge.Edge;
import com.sgwps.graph.graph.Graph;

public abstract class GraphSkeleton<T extends Edge> implements Graph<T>{
    VertexCollection vertexes;


    @Override
    public VertexCollection getVertexes() {
        return vertexes;
    }

    public GraphSkeleton(VertexCollection vertexCollection) {
        vertexes = vertexCollection;
    }

}
