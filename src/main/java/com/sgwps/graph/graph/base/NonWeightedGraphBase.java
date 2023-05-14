package com.sgwps.graph.graph.base;

import com.sgwps.graph.collections.EdgeCollection;
import com.sgwps.graph.collections.VertexCollection;
import com.sgwps.graph.edge.Edge;
import com.sgwps.graph.graph.NonWeightedGraph;

public class NonWeightedGraphBase<T extends Edge> extends GraphSkeleton<T> implements NonWeightedGraph<T>{


    

    EdgeCollection<T> edgeCollection;

    @Override
    public EdgeCollection<T> getEdges() {
       return edgeCollection;
    }

    public NonWeightedGraphBase(VertexCollection vertexCollection, EdgeCollection<T> edgeCollection){
        super(vertexCollection);
        this.edgeCollection = edgeCollection;
    }
    
}
