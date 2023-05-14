package com.sgwps.prague;
import java.util.Iterator;

public class RoutesIterator implements Iterable<String>{
    public Iterator<String> iterator = new Iterator<String>() {
        int i = 0;

        @Override
        public boolean hasNext() {
            return i < 26;
        }

        @Override
        public String next() {
            i++;
            return Integer.toString(i);
        }
        
    };

    @Override
    public Iterator<String> iterator() {
       return iterator;
    }
}
