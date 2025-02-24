import util.Edge;
import util.Graph2;
import util.Node;

import java.util.PriorityQueue;
import java.util.Stack;

public class AStarGraphProblem {
    private int optimizedValue;
    private Node finalNode;
    private final Graph2 graph;

    public AStarGraphProblem(Graph2 graph) {
        this.graph = graph;
    }

    public void solveAStar(String source, String dest) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        String sourceName = source.split("-")[0];
        Node sourceNode = graph.getNode(sourceName);
        priorityQueue.add(sourceNode);
        String destName = dest.split("-")[0];

        while (!priorityQueue.isEmpty()) {
            Node currentNode = priorityQueue.poll();

            if (currentNode.getName().equals(destName)) {
                optimizedValue = currentNode.getLastEdgeWeight();
                finalNode = currentNode;
                return;
            }
            for (Edge edge : graph.adj(currentNode.getName())) {
                Node node = edge.to();
                int g = currentNode.getLastEdgeWeight() + edge.getWeight();
                int f = g + node.getEvaluateToDest();
                priorityQueue.add(new Node(node.getName(), f, edge.getWeight() + currentNode.getLastEdgeWeight(), currentNode));
            }
        }
    }

    public static void main(String[] args) {
        Graph2 graph = new Graph2();

        // Initialize the Graph
        graph.addEdge("A-14", "C-15", 9);
        graph.addEdge("A-14", "D-6", 7);
        graph.addEdge("A-14", "E-8", 13);
        graph.addEdge("A-14", "F-7", 20);

        graph.addEdge("C-15", "H-10", 6);

        graph.addEdge("D-6", "E-8", 4);
        graph.addEdge("D-6", "H-10", 8);

        graph.addEdge("E-8", "K-2", 4);
        graph.addEdge("E-8", "I-4", 3);

        graph.addEdge("F-7", "G-12", 4);
        graph.addEdge("F-7", "I-4", 6);

        graph.addEdge("H-10", "K-2", 5);

        graph.addEdge("K-2", "B-0", 6);

        graph.addEdge("I-4", "B-0", 5);
        graph.printGraph();

        // Print the graph
        AStarGraphProblem aStarGraphProblem = new AStarGraphProblem(graph);
        aStarGraphProblem.solveAStar("A-14", "B-0");
        System.out.println(aStarGraphProblem.optimizedValue);
        Node temp = aStarGraphProblem.finalNode;
        Stack<String> stack = new Stack<>();
        while (temp != null) {
            stack.push(temp.toString());
            temp = temp.getLastNode();
        }
        System.out.println("Path: ");
        System.out.print(stack.pop());
        while (!stack.isEmpty()) {
            System.out.print(" -> " + stack.pop());
        }
    }
}
