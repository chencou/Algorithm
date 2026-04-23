package com.charizard.dataStructure.Graph;

import java.util.*;

/**
 *  图
 *  <p>领接矩阵图</p>
 */
public class Graph {

    private int vertices;

    private int[][] matrix;

    public Graph(int vertices) {
        this.vertices = vertices;
        this.matrix = new int[vertices][vertices];
    }

    /**
     * 添加边
     * @param start
     * @param end
     */
    public void addEdge(int start, int end) {
        matrix[start][end] = 1;
        matrix[end][start] = 1;
    }

    /**
     * 添加带权值边
     * @param start
     * @param end
     * @param weight
     */
    public void addEdge(int start, int end, int weight) {
        matrix[start][end] = weight;
        matrix[end][start] = weight;
    }

    public void printGraph() {
        for (int i = 0; i < vertices; i++) {
            System.out.print("Vertex " + i + " is connected to: ");
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    //广度优先搜索
    public void bfs(int start) {
        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.add(start);
        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + " ");
            for (int i = 0; i < matrix[current].length; i++) {
                if (matrix[current][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }

    }

    //狄克斯特拉 算法最短路径

    public void dijkstra(int start) {
        // dist[i] 存储从源点到顶点 i 的最短距离
        int[] dist = new int[vertices];
        // prev[i] 存储最短路径上顶点 i 的前一个顶点，用于路径恢复
        int[] pre = new int[vertices];
        // 标记顶点是否已确定最短路径
        boolean[] visited = new boolean[vertices];

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(pre, -1);
        dist[start] = 0;

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparing(o -> o[0]));
        queue.add(new int[]{0, start});
        while (!queue.isEmpty()) {
            //获取当前顶点
            int[] current = queue.poll();
            int currentVertex = current[1];
            if (visited[currentVertex]) continue;
            visited[currentVertex] = true;

            for (int i = 0; i < matrix[currentVertex].length; i++) {
                if (!visited[i] && matrix[currentVertex][i] != 0 && dist[currentVertex] + matrix[currentVertex][i] < dist[i]) {
                    dist[i] = dist[currentVertex] + matrix[currentVertex][i];
                    pre[i] = currentVertex;
                    queue.add(new int[]{dist[i], i});
                }
            }
        }
        printResult(start, dist, pre);
    }


    //打印最短距离
    private void printResult(int source, int[]  dist, int[] pre) {
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

    private void printPath(int target, int[] pre) {
        if (pre[target] == -1) {
            System.out.print(target + " ");
        } else {
            printPath(pre[target], pre);
            System.out.print(target + " ");
        }
    }

    public static void main(String[] args) {
//        Graph graph = new Graph(4);
//        graph.addEdge(0, 1);
//        graph.addEdge(0, 2);
//        graph.addEdge(1, 3);
//        graph.printGraph();
//        graph.bfs(2);

        //(获取目标最短边)狄克斯特拉 算法
        //0->1=5,
        //0->2=4,
        //1->3=2,
        //3->3=4,
        //2->1=1,
        Graph graph1 = new Graph(4);
        graph1.addEdge(0, 1, 5);
        graph1.addEdge(0, 2, 4);
        graph1.addEdge(1, 3, 2);
        graph1.addEdge(2, 1, 1);
        graph1.addEdge(2, 3, 4);
        graph1.dijkstra(0);
    }
}
