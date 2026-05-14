package com.charizard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 近似算法
 */
public class ApproximationAlgorithm {

    /**
     * 边
     */
    public static class Edge {
        int src;
        int dest;
        int weight;

        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    public static Set<Integer> approximation(List<Edge> edges) {
        Set<Integer> cover = new HashSet<>();
        if (edges == null || edges.isEmpty()) {
            return cover;
        }
        Set<Edge> uncoveredEdges = new HashSet<>(edges);

        while (!uncoveredEdges.isEmpty()) {
            Edge edge = uncoveredEdges.iterator().next();
            cover.add(edge.src);
            cover.add(edge.dest);
            uncoveredEdges.removeIf(e -> e.src == edge.src || e.src == edge.dest || e.dest == edge.src || e.dest == edge.dest);
        }
        return cover;
    }

    public static void main(String[] args) {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 5));
        edges.add(new Edge(1, 2, 4));
        edges.add(new Edge(1, 4, 4));


        Set<Integer> result = approximation(edges);
        String resultToString = result.stream().map(Object::toString).collect(Collectors.joining("，"));
        System.out.println("近似覆盖：" + resultToString + "；");
        System.out.println("顶点数：" + result.size());

    }

}
