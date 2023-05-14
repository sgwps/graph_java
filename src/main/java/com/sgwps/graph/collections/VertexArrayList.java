package com.sgwps.graph.collections;

import java.util.ArrayList;
import java.util.Iterator;

import com.sgwps.graph.vertex.Indexable;

public class VertexArrayList<T extends Indexable> implements VertexCollection {

    ArrayList<T> list;

    public VertexArrayList(ArrayList<T> list) {
        this.list = list;
    }
    
    @Override
    public Indexable getVertexByIndex(int index) {
        return list.get(index);
    }

    @Override
    public Iterator<Indexable> iterator() {
        Iterator<Indexable> it = new Iterator<Indexable>() {

            Iterator<T> baseIterator = list.iterator();

            @Override
            public boolean hasNext() {
                return baseIterator.hasNext();
            }

            @Override
            public Indexable next() {
                return baseIterator.next();
            }
            
        };
        return it;
    }

    @Override
    public int size(){
        return list.size();
    }
    
}
