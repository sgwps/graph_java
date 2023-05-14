package com.sgwps.graph.edge;

import com.sgwps.graph.Addable;

public interface WeightedEdge<T extends Comparable<T> & Addable<T>> extends Comparable<WeightedEdge<T>>, Edge{
    public T getValue();


    @Override
    default public int compareTo(WeightedEdge<T> edge) {
        return getValue().compareTo(edge.getValue());
    }

}
