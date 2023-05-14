package com.sgwps.graph.collections;

import java.util.ArrayList;
import java.util.Iterator;

import com.sgwps.graph.edge.Edge;
import com.sgwps.graph.vertex.Indexable;

public interface EdgeCollection<T extends Edge> extends Iterable<T>{
    public Iterator<T> iterator();


    default public ArrayList<T> outgoingEdges(Indexable vertex) {

        ArrayList<T> result = new ArrayList<T>();
        for (T i : this)  {
            if (i.ContainsFirst(vertex)) {
                result.add(i);
            }
        }
        return result;

    }

    default public int getVertexDegree(Indexable vertex) {
        int result = 0;
        for (T i : this) {
            if (i.ContainsFirst(vertex)) {
                result++;
            }
        }

        return result;
    }

    default public ArrayList<Indexable> getAdjacentVertexes(Indexable vertex) {
        ArrayList<Indexable> result = new ArrayList<Indexable>();
        for (T i : this) {
            if (i.getLeft().equals(vertex)) {
                result.add(i.Opposite(vertex));
            }
        }
        return result;
    }

}
