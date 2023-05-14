package com.sgwps.graph.edge;

import com.sgwps.graph.vertex.Indexable;

import lombok.Getter;
import lombok.NonNull;

public class BaseEdge implements Edge{
    @Getter
    final Indexable left;

    @Getter
    final Indexable right;


    public BaseEdge(@NonNull Indexable left, @NonNull Indexable right) {
        this.left = left;
        this.right = right;
    }


    @Override
    public String toString(){
        return String.format("%d -> %d", getLeft().getIndex(), getRight().getIndex());
    }
    

}
