/*
 */
package com.kongmy.ds.p8;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author Kong My
 */
public abstract class AbstractGraph<V> implements Graph<V> {

    protected int size;
    protected List<V> vertices;
    protected List<List<Integer>> neighbours;

    protected AbstractGraph(V[] vertices, int[][] edges) {
        this.vertices = new LinkedList<>();
        this.neighbours = new LinkedList<>();

        for (int i = 0; i < vertices.length; i++) {
            this.vertices.add(vertices[i]);
            neighbours.add(new LinkedList<>());
        }

        for (int[] edge : edges) {
            neighbours.get(edge[0]).add(edge[1]);
            neighbours.get(edge[1]).add(edge[0]);
        }

        size = vertices.length;
    }

    protected AbstractGraph(List<V> verticles, List<Edge> edges) {
        this.vertices = new LinkedList<>(verticles);
        this.neighbours = new LinkedList<>();

        for (int i = 0; i < verticles.size(); i++) {
            neighbours.add(new LinkedList<>());
        }

        for (Edge edge : edges) {
            neighbours.get(edge.vertexA).add(edge.vertexB);
            neighbours.get(edge.vertexB).add(edge.vertexA);
        }

        size = vertices.size();
    }

    protected AbstractGraph(int[][] edges, int numberOfVertices) {
        this.vertices = new LinkedList<>();
        this.neighbours = new LinkedList<>();

        for (int i = 0; i < numberOfVertices; i++) {
            vertices.add((V) ((Integer) i));
            neighbours.add(new LinkedList<>());
        }

        for (int[] edge : edges) {
            neighbours.get(edge[0]).add(edge[1]);
            neighbours.get(edge[1]).add(edge[0]);
        }

        size = numberOfVertices;
    }

    protected AbstractGraph(List<Edge> edges, int numberOfVertices) {
        this.vertices = new LinkedList<>();
        this.neighbours = new LinkedList<>();

        for (int i = 0; i < numberOfVertices; i++) {
            vertices.add((V) ((Integer) i));
            neighbours.add(new LinkedList<>());
        }

        for (Edge edge : edges) {
            neighbours.get(edge.vertexA).add(edge.vertexB);
            neighbours.get(edge.vertexB).add(edge.vertexA);
        }

        size = numberOfVertices;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public List<V> getVerticles() {
        // Prevent use modify our list
        return new LinkedList<>(vertices);
    }

    @Override
    public V getVertex(int index) {
        return vertices.get(index);
    }

    @Override
    public int getIndex(V vertex) {
        return vertices.indexOf(vertex);
    }

    @Override
    public List<Integer> getNeighours(int index) {
        return neighbours.get(index);
    }

    @Override
    public int getDegree(int index) {
        int minDegree = getMinDegree(new LinkedList<>(), 0, index);
        return minDegree == Integer.MAX_VALUE ? -1 : minDegree;
    }

    @Override
    public int[][] getAdjacencyMatrix() {
        int[][] matrix = new int[size][size];

        List<Integer> neighbour;
        for (int row = 0; row < size; row++) {
            neighbour = neighbours.get(row);

            for (int col = 0; col < size; col++) {
                matrix[row][col] = neighbour.contains(col) ? 1 : 0;
            }
        }

        return matrix;
    }

    @Override
    public void printAdjacencyMatrix() {
        int[][] matrix = getAdjacencyMatrix();

        System.out.println("[");

        for (int row = 0; row < size; row++) {
            System.out.print("  [");

            for (int col = 0; col < size; col++) {
                System.out.print(matrix[row][col]);

                // Print comma for non-last-col
                if (col != size - 1) {
                    System.out.print(", ");
                }
            }

            System.out.print("]");

            // Print comma for non-last-row
            if (row != size - 1) {
                System.out.print(",");
            }

            System.out.println();
        }
        System.out.println("]");
    }

    @Override
    public void printEdges() {
        List<Integer> neighbourList;

        System.out.print("[");
        for (int row = 0; row < size; row++) {
            neighbourList = neighbours.get(row);

            System.out.print("\t[");
            for (int i = 0; i < neighbourList.size(); i++) {
                System.out.printf("{ %d, %d }", row, neighbourList.get(i));

                // Print comma for non-last-col
                if (i != neighbourList.size() - 1) {
                    System.out.print(", ");
                }
            }

            System.out.print("]");

            // Print comma for non-last-row
            if (row != size - 1) {
                System.out.print(",");
            }

            System.out.println();
        }
        System.out.println("]");
    }

    @Override
    public Tree dfs(int index) {
        Stack<Integer> visitStack = new Stack<>();
        List<Integer> visitedList = new LinkedList<>();
        int[] parents = new int[size];

        // Initialize
        for (int i = 0; i < size; i++) {
            parents[i] = Tree.NOT_FOUND;
        }
        parents[index] = Tree.NO_PARENT;
        visitStack.push(index);

        int currentItem;
        List<Integer> neighbourList;

        while (!visitStack.isEmpty()) {
            currentItem = visitStack.pop();
            visitedList.add(currentItem);

            neighbourList = getNeighours(currentItem);
            for (int neighbour : neighbourList) {
                if (!visitStack.contains(neighbour) && !visitedList.contains(neighbour)) {
                    visitStack.push(neighbour);
                    parents[neighbour] = currentItem;
                }
            }
        }

        return new Tree(index, parents, visitedList);
    }

    @Override
    public Tree bfs(int index) {
        Queue<Integer> visitQueue = new LinkedList<>();
        List<Integer> visitedList = new LinkedList<>();
        int[] parents = new int[size];

        // Initialize
        for (int i = 0; i < size; i++) {
            parents[i] = Tree.NOT_FOUND;
        }
        parents[index] = Tree.NO_PARENT;
        visitQueue.add(index);

        int currentItem;
        List<Integer> neighbourList;

        while (!visitQueue.isEmpty()) {
            currentItem = visitQueue.remove();
            visitedList.add(currentItem);

            neighbourList = getNeighours(currentItem);
            for (int neighbour : neighbourList) {
                if (!visitQueue.contains(neighbour) && !visitedList.contains(neighbour)) {
                    visitQueue.add(neighbour);
                    parents[neighbour] = currentItem;
                }
            }
        }

        return new Tree(index, parents, visitedList);
    }

    private int getMinDegree(List<Integer> visited, int start, int end) {
        int min = Integer.MAX_VALUE;
        visited.add(start);
        
        for (int neighbour : neighbours.get(start)) {

            if(visited.contains(neighbour))
                continue;
            
            // If it is this neighbour
            if (neighbour == end) {
                return 1;
            }

            // Get the minimum from this neighbour
            else {
                min = Math.min(min, getMinDegree(visited, neighbour, end));
            }
        }

        // The target was found somewhere
        if (min < Integer.MAX_VALUE) {
            // +1 to include this
            return 1 + min;
        }
        // Not found at all
        else {
            return min;
        }
    }

    public static class Edge {

        protected final int vertexA;
        protected final int vertexB;

        public Edge(int vertexA, int vertexB) {
            this.vertexA = vertexA;
            this.vertexB = vertexB;
        }

    }

    public class Tree {

        public static final int NO_PARENT = -1;
        public static final int NOT_FOUND = -2;

        private final int root;
        private final int verticesFound;
        private final int[] parents;
        private final List<Integer> searchOrders;

        public Tree(int root, int[] parent) {
            this(root, parent, null);
        }

        public Tree(int root, int[] parents, List<Integer> searchOrders) {
            this.root = root;
            this.parents = parents;
            this.searchOrders = searchOrders;

            int count = 0;
            for (int parent : parents) {
                if (parent != NOT_FOUND) {
                    count++;
                }
            }
            this.verticesFound = count;
        }

        public int getRoot() {
            return root;
        }

        public int[] getParents() {
            return parents;
        }

        public List<Integer> getSearchOrders() {
            return searchOrders;
        }

        public int getNumberOfVerticesFound() {
            return verticesFound;
        }

        public Iterator<V> pathIterator(int index) {
            if (parents[index] == NOT_FOUND) {
                return null;
            }
            else {
                Stack<Integer> parentStack = new Stack<>();
                int currentIndex = index;

                // Iterate from current index till root
                while (currentIndex != root) {
                    parentStack.push(currentIndex);
                    currentIndex = parents[currentIndex];
                }

                // Convert parent stack to queue
                Queue queue = new LinkedList<>();
                while (!parentStack.isEmpty()) {
                    queue.add(vertices.get(parentStack.pop()));
                }
                return queue.iterator();
            }
        }

        public void printPath(int index) {
            Iterator<V> it = pathIterator(index);
            List<String> pathList = new LinkedList<>();
            while (it.hasNext()) {
                pathList.add(it.next().toString());
            }
            System.out.println(String.join(" => ", pathList));
        }

        public void printTree() {
            printTree(root);
            System.out.println();
        }

        private void printTree(int parent) {
            System.out.print(vertices.get(parent).toString());

            for (int i = 0; i < parents.length; i++) {
                if (parents[i] == parent) {
                    printTree(i);
                }
            }
        }

    }

}
