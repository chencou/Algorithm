
package com.charizard.dataStructure.Graph;

import java.util.*;

/**
 * 邻接表图
 *  仿造 Python
 *  <p>grah = ["张三","李四"]</p>
 *  <p>grah["张三"] = ["王五","赵六"]</p>
 *
 */
public class PGraph {

    private GraphNode root;

    public static class GraphNode {
        private String name;
        private List<GraphNode> adj;

        public GraphNode(String name) {
            this.name = name;
            this.adj = new ArrayList<>();
        }

        public void addNeighbor(GraphNode neighbor) {
            this.adj.add(neighbor);
        }

        public String getName() {
            return name;
        }

        public List<GraphNode> getAdj() {
            return adj;
        }
    }

    private final Map<String, GraphNode> nodeMap;

    public PGraph() {
        this.nodeMap = new HashMap<>();
    }

    public GraphNode createNode(String name) {
        GraphNode node = new GraphNode(name);
        nodeMap.put(name, node);
        return node;
    }

    public GraphNode getNode(String name) {
        return nodeMap.get(name);
    }

    public void setRoot(GraphNode root) {
        this.root = root;
    }

    public static void main(String[] args) {
        PGraph graph = new PGraph();

        GraphNode zhangSan = graph.createNode("张三");
        GraphNode liSi = graph.createNode("李四");
        GraphNode wangWu = graph.createNode("王五");
        GraphNode zhaoLiu = graph.createNode("赵六");
        GraphNode tianQi = graph.createNode("田七");

        zhangSan.addNeighbor(liSi);
        zhangSan.addNeighbor(wangWu);
        zhangSan.addNeighbor(zhaoLiu);

        liSi.addNeighbor(zhaoLiu);
        liSi.addNeighbor(tianQi);

        graph.setRoot(zhangSan);

        GraphNode result = graph.bfs("王五");
        if (result != null) {
            System.out.println("找到: " + result.getName());
        } else {
            System.out.println("未找到目标");
        }
    }

    public boolean personIsSeller(String name) {
        return Objects.equals(name, "王五");
    }

    //广度优先搜索
    public GraphNode bfs(String targetName) {
        Queue<GraphNode> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        GraphNode node = getNode(targetName);
        if (Objects.isNull(node)) {
            return null;
        }
        queue.offer(node);
        visited.add(node.getName());

        while (!queue.isEmpty()) {
            GraphNode current = queue.poll();

            //判断当前节点是否是目标节点
            if (personIsSeller(current.getName())) {
                return current;
            }
            //遍历当前节点的邻接节点
            for (GraphNode neighbor : current.getAdj()) {
                if (!visited.contains(neighbor.getName())) {
                    visited.add(neighbor.getName());
                    queue.offer(neighbor);
                }
            }
        }

        return null;
    }

    public GraphNode findNodeByName(String name) {
        return nodeMap.get(name);
    }
}