/*
 */
package com.kongmy.ds.p8;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Kong My
 */
public class UnweightedGraph<V> extends AbstractGraph<V> {

    public UnweightedGraph(V[] vertices, int[][] edges) {
        super(vertices, edges);
    }

    public UnweightedGraph(List<V> verticles, List<Edge> edges) {
        super(verticles, edges);
    }

    public UnweightedGraph(int[][] edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }

    public UnweightedGraph(List<Edge> edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }

}
