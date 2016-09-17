/*
 */
package com.kongmy.ds.p8;

/**
 *
 * @author Kong My
 */
public class Question1 {

    public static void main(String[] args) {
        int[][] edges = new int[][] {
            {0, 1}, {1, 2}, {2, 4}, {2, 3}, {3, 4}            
        };
        
        UnweightedGraph<Integer> graph = new UnweightedGraph<>(edges, 6);
        AbstractGraph.Tree bfs = graph.bfs(0);
        AbstractGraph.Tree dfs = graph.dfs(0);
        
        graph.printAdjacencyMatrix();
        graph.printEdges();
        
        System.out.println(graph.getDegree(4));
        System.out.println(graph.getDegree(5));
        
        bfs.printTree();
        dfs.printTree();
        
        bfs.printPath(4);
        dfs.printPath(4);

    }

}
