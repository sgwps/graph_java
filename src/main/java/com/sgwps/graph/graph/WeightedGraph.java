package com.sgwps.graph.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.sgwps.graph.Addable;
import com.sgwps.graph.collections.EdgeCollection;
import com.sgwps.graph.collections.VertexCollection;
import com.sgwps.graph.edge.WeightedEdge;
import com.sgwps.graph.vertex.Indexable;

public interface WeightedGraph<T extends Comparable<T> & Addable<T>> extends Graph<WeightedEdge<T>> {


    public T getZeroValue();

    public T getMaxValue();

    default public ArrayList<T> Dijkstra(Indexable vertex) {
        VertexSearchFlags[] flags = getInitialSerachArray();

        flags[vertex.getIndex()] = VertexSearchFlags.BLACK;

        ArrayList<T> results = new ArrayList<T>(Collections.nCopies(getVertexes().size(), null));

        results.set(vertex.getIndex(), getZeroValue());

        while (vertex != null) {
            List<WeightedEdge<T>> outgoingEdges = getEdges().outgoingEdges(vertex);
            for (WeightedEdge<T> outgoingEdge : outgoingEdges) {
                Indexable right = outgoingEdge.getRight();
                T newValue = results.get(vertex.getIndex()).add(outgoingEdge.getValue());
                if (results.get(right.getIndex()) == null || results.get(right.getIndex()).compareTo(newValue) > 0) {
                    results.set(right.getIndex(), newValue);
                }

            }
            flags[vertex.getIndex()] = VertexSearchFlags.BLACK;
            vertex = null;
            T way = getMaxValue();
            VertexCollection vertexes = getVertexes();
            for (Indexable i : vertexes) {
                if (flags[i.getIndex()] == VertexSearchFlags.WHITE && way.compareTo(results.get(i.getIndex())) > 0) {
                    way = results.get(i.getIndex());
                    vertex = i;
                }
            }
        }
        return results;

    }

}
