package com.sgwps.graph.graph.base;

import com.google.common.base.Supplier;
import com.sgwps.graph.Addable;
import com.sgwps.graph.collections.EdgeCollection;
import com.sgwps.graph.collections.VertexCollection;
import com.sgwps.graph.edge.WeightedEdge;
import com.sgwps.graph.graph.Graph;
import com.sgwps.graph.graph.WeightedGraph;

public class WeightedGraphBase<T extends Comparable<T> & Addable<T>> extends GraphSkeleton<WeightedEdge<T>> implements WeightedGraph<T> {

    EdgeCollection<WeightedEdge<T>> edges;
    Supplier<T> zeroSupplier;
    Supplier<T> maxSupplier;


    public WeightedGraphBase(VertexCollection vertexCollection, EdgeCollection<WeightedEdge<T>> edges, Supplier<T> zeroSupplier, Supplier<T> maxSupplier) {
        super(vertexCollection);
        this.edges = edges;
        this.zeroSupplier = zeroSupplier;
        this.maxSupplier = maxSupplier;
    }

    @Override
    public EdgeCollection<WeightedEdge<T>> getEdges() {
        return edges;
    }

    @Override
    public T getZeroValue() {
        return zeroSupplier.get();
    }

    @Override
    public T getMaxValue() {
        return maxSupplier.get();
    }
    
}
