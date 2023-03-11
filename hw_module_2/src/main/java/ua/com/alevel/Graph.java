package ua.com.alevel;

import java.util.*;

public class Graph {

    public static final int MAX_COST = 200000;
    private final Map<String, List<Edge>> graph = new HashMap<>();

    public void addName(String name) {
        graph.put(name, new ArrayList<>());
    }

    public void addEdge(String name, String end, int cost) {
        graph.get(name).add(new Edge(end, cost));
    }

    public int DPQ(String start, String end) {
        Map<String, Integer> dist = new HashMap<>();
        PriorityQueue<Node> queue = new PriorityQueue<>();
        for (String name : graph.keySet()) {
            if (name.equals(start)) {
                dist.put(name, 0);
                queue.add(new Node(name, 0));
            } else {
                dist.put(name, MAX_COST);
                queue.add(new Node(name, MAX_COST));
            }
        }
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.name.equals(end)) {

                return dist.get(end);
            }
            if (dist.get(node.name) == MAX_COST) {
                break;
            }
            for (Edge edge : graph.get(node.name)) {
                int sum = dist.get(node.name) + edge.cost;
                if (sum < dist.get(edge.to)) {
                    dist.put(edge.to, sum);
                    queue.add(new Node(edge.to, sum));
                }
            }
        }
        return -1;
    }

    private static class Node implements Comparable<Node> {
        public String name;
        public int cost;

        public Node(String name, int cost) {
            this.name = name;
            this.cost = cost;
        }

        public Node() {
        }

        @Override
        public int compareTo(Node node2) {
            return Integer.compare(cost, node2.cost);
        }
    }

    private static class Edge {
        String to;
        int cost;

        public Edge(String to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
}
