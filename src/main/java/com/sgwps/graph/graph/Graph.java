package com.sgwps.graph.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Queue;

import com.google.common.collect.Iterators;
import com.sgwps.graph.collections.EdgeCollection;
import com.sgwps.graph.collections.VertexCollection;
import com.sgwps.graph.edge.Edge;
import com.sgwps.graph.vertex.Indexable;

public interface Graph<T extends Edge> {
    default public VertexSearchFlags[] getInitialSerachArray() {
        VertexSearchFlags[] flags = new VertexSearchFlags[getVertexes().size()];
        Arrays.fill(flags, VertexSearchFlags.WHITE);
        return flags;
    }

    public VertexCollection getVertexes();
    public EdgeCollection<T> getEdges();

    default public int getVertexDegree(int index) {
        return getVertexDegree(getVertexes().getVertexByIndex(index));
    }


    default public int getVertexDegree(Indexable vertex) {
        return getEdges().getVertexDegree(vertex);
    }


    default public int getComponentCount() {
        int result = 0;
        VertexSearchFlags[] flags = getInitialSerachArray();
        for (int i = 0; i < flags.length; i++) {
            if (flags[i] == VertexSearchFlags.WHITE) {
                result++;
                DFS(flags, getVertexes().getVertexByIndex(i));
            }
        }
        return result;
    }

    default public void DFS(VertexSearchFlags[] flags, Indexable vertex) {
        ArrayList<Indexable> adjastent = getEdges().getAdjacentVertexes(vertex);
        for (Indexable i : adjastent) {
            if (flags[i.getIndex()] == VertexSearchFlags.WHITE) {
                flags[i.getIndex()] = VertexSearchFlags.GREY;
                DFS(flags, i);
            }
            flags[i.getIndex()] = VertexSearchFlags.BLACK;
        }

    }

    default public ArrayList<Indexable> ShotestRouteByPrevious(Indexable vertex) {
        ArrayList<Indexable> results = new ArrayList<Indexable>(Collections.nCopies(getVertexes().size(), null));
        Queue<Indexable> queue = new ArrayDeque<Indexable>();
        queue.add(vertex);
        results.set(vertex.getIndex(), vertex);
        while (!queue.isEmpty()) {
            Indexable currVertex = queue.poll();
            ArrayList<Indexable> adjastent = getEdges().getAdjacentVertexes(currVertex);
            for (Indexable i : adjastent) {
                if (results.get(i.getIndex()) == null) {
                    results.set(i.getIndex(), currVertex);
                    queue.add(i);
                }
            }
        }
        return results;
    }

    default public ArrayList<Integer> ShortestRoutes(Indexable vertex) {
        ArrayList<Integer> results = new ArrayList<Integer>(Collections.nCopies(getVertexes().size(), -1));
        Queue<Indexable> queue = new ArrayDeque<Indexable>();
        results.set(vertex.getIndex(), 0);
        queue.add(vertex);
        while (!queue.isEmpty()) {
            Indexable currVertex = queue.poll();
            ArrayList<Indexable> adjastent = getEdges().getAdjacentVertexes(currVertex);
            for (Indexable i : adjastent) {
                if (results.get(i.getIndex()) == -1) {
                    results.set(i.getIndex(), results.get(currVertex.getIndex()) + 1);
                    queue.add(i);
                }
            }
        }
        return results;
    }

    

}
