package com.sgwps.graph.edge;

import com.sgwps.graph.vertex.Indexable;

public interface Edge {
    public Indexable getLeft();
    public Indexable getRight();


    public default boolean ContainsFirst(Indexable i) {
        if (getLeft().equals(i)) {
            return true;
        }
        return false;
    }
    public default Indexable Opposite(Indexable i) {
        if (i == getLeft()) {
            return getRight();
        }
        return getLeft();
    }
}
