package com.sgwps.graph.collections;

import java.util.Iterator;

import com.google.common.collect.Iterators;
import com.sgwps.graph.vertex.Indexable;

public interface VertexCollection extends Iterable<Indexable>{
    public Indexable getVertexByIndex(int index);

    default public int size() {
        Iterator<Indexable> iterator = iterator();
        return Iterators.size(iterator);
    }


}
