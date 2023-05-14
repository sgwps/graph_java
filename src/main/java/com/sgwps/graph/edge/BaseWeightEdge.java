package com.sgwps.graph.edge;

import com.sgwps.graph.Addable;
import com.sgwps.graph.vertex.Indexable;

import lombok.Getter;
import lombok.Setter;

public class BaseWeightEdge<T extends Comparable<T> & Addable<T>> extends BaseEdge implements WeightedEdge<T> {
    @Getter
    @Setter
    T value;

    public BaseWeightEdge (Indexable left, Indexable right, T value) {
        super(left, right); 
        this.value = value;
    }

   

    

}
