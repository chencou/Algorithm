package com.charizard.dataStructure.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 链表图
 *
 */
public class ListGraph {

    private int vertices;

    private List<List<Integer>> adjList;

    public ListGraph(int vertices) {
        this.vertices = vertices;
        this.adjList = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            this.adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int src, int dest) {
        this.adjList.get(src).add(dest);
        this.adjList.get(dest).add(src);
    }

    public void printGraph() {
        for (int i = 0; i < vertices; i++) {
            System.out.print("Vertex " + i + " is connected to: ");
            for (int j = 0; j < adjList.get(i).size(); j++) {
                System.out.print(adjList.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    public  void bfs(Integer start) {
        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.offer(start);

        while (!queue.isEmpty()) {
            Integer current = queue.poll();
            System.out.print(current + " ");
            for (Integer neighbor : adjList.get(current)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
    }

    public static void main(String[] args) {
        ListGraph graph = new ListGraph(4);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);

        graph.printGraph();
        graph.bfs(2);
    }
}
