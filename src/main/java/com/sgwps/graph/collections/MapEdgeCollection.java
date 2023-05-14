package com.sgwps.graph.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.sgwps.graph.edge.Edge;
import com.sgwps.graph.vertex.Indexable;

public class MapEdgeCollection<T extends Edge> extends HashMap<Integer, ArrayList<T>> implements EdgeCollection<T>  {



    public MapEdgeCollection(ArrayList<T> list) {
        super();
        for (T i : list) {
            if (!this.containsKey(i.getLeft().getIndex())) {
                this.put(i.getLeft().getIndex(), new ArrayList<T>());
            }
            this.get(i.getLeft().getIndex()).add(i);

        }
        
    }

    @Override
    public Iterator<T> iterator() {
        Collection<ArrayList<T>> sets = this.values();

        Iterator<T> it = new Iterator<T>() {
            Iterator<ArrayList<T>> setIterator = sets.iterator();

            Iterator<T> curList = setIterator.next().iterator();

            @Override
            public boolean hasNext() {
                return setIterator.hasNext();
            }

            @Override
            public T next() {
                T result = curList.next();
                if (!curList.hasNext()) {
                    curList = setIterator.next().iterator();
                }
                return result;
            }

        };
        return it;
    }

    

    @Override
    public ArrayList<T> outgoingEdges(Indexable vertex) {
        return this.get(vertex.getIndex());

    }

    @Override
    public int getVertexDegree(Indexable vertex) {

        return outgoingEdges(vertex).size();
    }

    @Override
    public ArrayList<Indexable> getAdjacentVertexes(Indexable vertex) {
        ArrayList<T> preResult = outgoingEdges(vertex);
        ArrayList<Indexable> result = new ArrayList<Indexable>();
        for (T i : preResult) {
            result.add(i.getRight());
        }
        return result;
    }

}
