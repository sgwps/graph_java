package com.sgwps.graph.collections;

import java.util.ArrayList;
import java.util.Iterator;

import com.sgwps.graph.edge.Edge;
import com.sgwps.graph.vertex.Indexable;

public class MatrixEdgeCollection<T extends Edge> implements EdgeCollection<T> {

    private T[][] matrix;


    public MatrixEdgeCollection(T[][] matrix) {
        this.matrix = matrix;
    }

    public MatrixEdgeCollection(ArrayList<T> edges, int maxVertexIndex) {
        matrix = (T[][]) new Edge[maxVertexIndex + 1][maxVertexIndex + 1];
        for (T i :edges) {
            matrix[i.getLeft().getIndex()][i.getRight().getIndex()] = i;

        }

    }

   

    @Override
    public ArrayList<T> outgoingEdges(Indexable vertex) {
        ArrayList<T> result = new ArrayList<T>();
        for (T i : matrix[vertex.getIndex()]) {
            if (i != null)
                result.add(i);
        }
        return result;
    }

    @Override
    public int getVertexDegree(Indexable vertex) {
        int result = 0;
        for (T i : matrix[vertex.getIndex()]) {
            if (i != null)
                result++;
        }

        return result;
    }

    @Override
    public ArrayList<Indexable> getAdjacentVertexes(Indexable vertex) {
        ArrayList<Indexable> result = new ArrayList<Indexable>();
        for (T i : matrix[vertex.getIndex()]) {
            if (i != null)
                result.add(i.getRight());
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<T>() {

            private int currentIndex = 0;

            private int maxIndex = matrix.length * matrix[0].length - 1;

            private T getElement(int index) {
                return matrix[index / matrix.length][index % matrix[0].length];
            }

            {
                while (maxIndex >= 0 && getElement(maxIndex) == null) {
                    maxIndex--;
                }

                while (currentIndex <= maxIndex && getElement(currentIndex) == null) {
                    currentIndex++;
                }
            }

            @Override
            public boolean hasNext() {
                return currentIndex < maxIndex;
            }

            @Override
            public T next() {
                T result = getElement(currentIndex);
                currentIndex++;
                while (currentIndex <= maxIndex && getElement(currentIndex) == null) {
                    currentIndex++;
                }
                return result;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }

}
