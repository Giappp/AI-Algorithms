package util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Graph2 {
    public Map<String, List<Edge>> adjList;

    public Graph2() {
        adjList = new LinkedHashMap<>();
    }

    public void addEdge(String source, String destination, int weight) {
        Node sourceNode = convertStringToNode(source);
        Node destNode = convertStringToNode(destination);
        adjList.putIfAbsent(sourceNode.getName(), new ArrayList<>());
        adjList.get(sourceNode.getName()).add(new Edge(sourceNode, destNode, weight));
    }

    public List<Edge> adj(String source) {
        return adjList.get(source);
    }

    public Node getNode(String source) {
        return adj(source).get(0).from();
    }

    private Node convertStringToNode(String string) {
        String[] parts = string.split("-");
        return new Node(parts[0], Integer.parseInt(parts[1]));
    }

    public void printGraph() {
        for (String node : adjList.keySet()) {
            System.out.print(node + " -> ");
            for (Edge edge : adjList.get(node)) {
                System.out.print(edge + " ");
            }
            System.out.println();
        }
    }
}
