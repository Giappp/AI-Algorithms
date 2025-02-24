package util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    public Map<Node, List<Edge>> adjList;

    public Graph() {
        adjList = new LinkedHashMap<>();
    }

    public void addEdge(String source, String destination, int weight) {
        Node sourceNode = getNode(source);
        Node destNode = getNode(destination);
        if (sourceNode == null && destNode == null) {
            sourceNode = convertStringToNode(source);
            destNode = convertStringToNode(destination);
            adjList.put(sourceNode, new ArrayList<>());
            adjList.get(sourceNode).add(new Edge(sourceNode, destNode, weight));
            return;
        }
        if (sourceNode == null && destNode != null) {
            sourceNode = convertStringToNode(source);
            adjList.put(sourceNode, new ArrayList<>());
            adjList.get(sourceNode).add(new Edge(sourceNode, destNode, weight));
            return;
        }
        if (sourceNode != null && destNode == null) {
            destNode = convertStringToNode(destination);
            adjList.get(sourceNode).add(new Edge(sourceNode, destNode, weight));
            return;
        }
        adjList.get(sourceNode).add(new Edge(sourceNode, destNode, weight));
    }

    public List<Edge> adj(Node source) {
        return adjList.get(source);
    }

    public Node getNode(String source) {
        if (source.contains("-")) {
            String[] parts = source.split("-");
            String namePart = parts[0];
            for (Node node : adjList.keySet()) {
                if (node.getName().equals(namePart)) return node;
            }
            return null;
        }
        for (Node node : adjList.keySet()) {
            if (node.getName().equals(source)) return node;
        }
        return null;
    }


    private Node convertStringToNode(String string) {
        String[] parts = string.split("-");
        return new Node(parts[0], Integer.parseInt(parts[1]));
    }

    public void printGraph() {
        for (Node node : adjList.keySet()) {
            System.out.print(node + " -> ");
            for (Edge edge : adjList.get(node)) {
                System.out.print(edge + " ");
            }
            System.out.println();
        }
    }
}
