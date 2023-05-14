package com.sgwps.graph.collections;

import java.util.ArrayList;

import com.sgwps.graph.edge.Edge;

public class EdgeArrayList<T extends Edge> extends ArrayList<T> implements EdgeCollection<T>  {

    public EdgeArrayList(ArrayList<T> list){
        super(list);
    }


   
    
}
