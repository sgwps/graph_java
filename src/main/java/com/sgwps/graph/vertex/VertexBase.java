package com.sgwps.graph.vertex;

import lombok.Getter;

public class VertexBase implements Indexable{
    @Getter
    int index;
    
    public VertexBase(int index){
        this.index = index;
    }
    
}
