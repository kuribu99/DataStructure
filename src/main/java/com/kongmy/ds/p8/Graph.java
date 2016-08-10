/*
 */
package com.kongmy.ds.p8;

import java.util.List;

/**
 *
 * @author Kong My
 */
public interface Graph<V> {

    int getSize();

    List<V> getVerticles();

    V getVertex(int index);

    int getIndex(V vertex);

    List<Integer> getNeighours(int index);

    // Number of edges starting from index 0 till index
    int getDegree(int index);

    int[][] getAdjacencyMatrix();

    void printAdjacencyMatrix();

    void printEdges();

    AbstractGraph<V>.Tree dfs(int index);

    AbstractGraph<V>.Tree bfs(int index);

}
