package com.charizard.dataStructure.Graph;

import java.util.*;

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
            List<Integer> list = new ArrayList<>(vertices);
            for (int j = 0; j < vertices; j++) {
                list.add(0);
            }
            this.adjList.add(i, list);
        }
    }

    public void addEdge(int src, int dest) {
        this.adjList.get(src).set(dest, 0);
        this.adjList.get(dest).set(src, 0);
    }

    public void addEdge(int src, int dest, int weight) {
        this.adjList.get(src).set(dest, weight);
        this.adjList.get(dest).set(src, weight);
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

    //狄克斯特拉 算法最短路径
    public void dijkstra(int start) {
        int[] dist = new int[vertices];
        int[] pre = new int[vertices];
        boolean[] visited = new boolean[vertices];

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(pre, -1);

        dist[start] = 0;
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        queue.offer(new int[]{0, start});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentVertex = current[1];
            if (visited[currentVertex]) continue;
            visited[currentVertex] = true;
            int size = adjList.get(currentVertex).size();
            for (int i = 0; i < size; i++) {
                //获取当前顶点
                int neighbor = adjList.get(currentVertex).get(i);
                if (!visited[i]  && neighbor != 0 && dist[currentVertex] + neighbor < dist[i]) {
                    dist[i] = dist[currentVertex] + neighbor;
                    pre[i] = currentVertex;
                    queue.offer(new int[]{dist[i], i});
                }
            }
        }
        printResult(start, dist, pre);
    }

    public void printResult(int source, int[]  dist, int[] pre) {
        System.out.println("最短距离：");
        System.out.println("从源点 " + source + " 出发的最短路径:");
        for (int i = 0; i < dist.length; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println("到顶点 " + i + " 不可达");
            } else {
                System.out.print("到顶点 " + i + " 的最短距离: " + dist[i]);
                System.out.print("，路径: ");
                printPath(i, pre);
                System.out.println();
            }
        }
    }

    public void printPath(int target, int[] pre) {
        if (pre[target] == -1) {
            System.out.print(target + " ");
        } else {
            printPath(pre[target], pre);
            System.out.print(target + " ");
        }
    }

    public static void main(String[] args) {
//        ListGraph graph = new ListGraph(4);
//        graph.addEdge(0, 2);
//        graph.addEdge(1, 3);
//
//        graph.printGraph();
//        graph.bfs(2);

        ListGraph graph1 = new ListGraph(4);
        graph1.addEdge(0, 1, 5);
        graph1.addEdge(0, 2, 4);
        graph1.addEdge(1, 3, 2);
        graph1.addEdge(2, 1, 1);
        graph1.addEdge(2, 3, 4);

        graph1.dijkstra(0);
    }
}
