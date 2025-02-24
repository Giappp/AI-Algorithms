package util;

public class Edge implements Comparable<Edge> {
    private final Node v;
    private final Node w;
    private final int weight;

    public Edge(Node v, Node w, int weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public Node from() {
        return v;
    }

    public Node to() {
        return w;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.weight, o.weight);
    }

    @Override
    public String toString() {
        return String.format("%s %d", w, weight);
    }
}
