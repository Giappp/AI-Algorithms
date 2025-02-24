package util;

public class Node implements Comparable<Node> {
    private String name;
    private int evaluateToDest;
    private int lastEdgeWeight;
    private Node lastNode;

    public Node() {
    }

    public Node(String name, int evaluateToDest) {
        this.name = name;
        this.evaluateToDest = evaluateToDest;
        lastEdgeWeight = 0;
        lastNode = null;
    }

    public Node(String name, int evaluateToDest, int lastEdgeWeight, Node lastNode) {
        this.name = name;
        this.evaluateToDest = evaluateToDest;
        this.lastEdgeWeight = lastEdgeWeight;
        this.lastNode = lastNode;
    }

    public int getEvaluateToDest() {
        return evaluateToDest;
    }

    public int getLastEdgeWeight() {
        return lastEdgeWeight;
    }

    public void setLastEdgeWeight(int lastEdgeWeight) {
        this.lastEdgeWeight = lastEdgeWeight;
    }

    public Node getLastNode() {
        return lastNode;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.evaluateToDest, o.evaluateToDest);
    }

    @Override
    public String toString() {
        return String.format("%s-%d", name, evaluateToDest);
    }
}
