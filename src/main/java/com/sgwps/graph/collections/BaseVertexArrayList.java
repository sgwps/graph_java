package com.sgwps.graph.collections;

import java.util.ArrayList;

import com.sgwps.graph.vertex.VertexBase;

public class BaseVertexArrayList extends VertexArrayList<VertexBase> {

    public BaseVertexArrayList(ArrayList<VertexBase> list) {
        super(list);
    }

    static private ArrayList<VertexBase>  GenerateArrayList(int count){
        ArrayList<VertexBase> result = new ArrayList<VertexBase>(count);
        for (int i = 0; i < count; i++) {
            result.set(i, new VertexBase(i));
        }
        return result;
    }

    public BaseVertexArrayList(int count) {
        super(GenerateArrayList(count));
    }
    
}
