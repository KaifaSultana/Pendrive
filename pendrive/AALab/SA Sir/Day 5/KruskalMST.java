import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Edge {
    int src, dest, weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

public class KruskalMST {

    // Function to create a graph with V vertices
    public static List<Edge> createGraph(int V) {
        return new ArrayList<>();
    }

    // Find function for the union-find data structure
    public static int find(int[] parent, int i) {
        if (parent[i] != i) {
            parent[i] = find(parent, parent[i]);
        }
        return parent[i];
    }

    // Union function for the union-find data structure
    public static void unionSets(int[] parent, int[] rank, int x, int y) {
        int rootX = find(parent, x);
        int rootY = find(parent, y);

        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }

    // Kruskal's algorithm to find the Minimum Spanning Tree
    public static void kruskalMST(int V, List<Edge> graph) {
        List<Edge> result = new ArrayList<>(); // This will store the resulting MST
        int totalWeight = 0; // Variable to store the total weight of the MST

        // Step 1: Sort all the edges in non-decreasing order of their weight
        Collections.sort(graph, Comparator.comparingInt(edge -> edge.weight));

        // Initialize union-find data structures
        int[] parent = new int[V];
        int[] rank = new int[V];

        for (int v = 0; v < V; ++v) {
            parent[v] = v;
        }

        // Step 2: Pick the smallest edge and add it to the MST if it doesn't form a cycle
        for (Edge edge : graph) {
            int setU = find(parent, edge.src); // 0-based indexing
            int setV = find(parent, edge.dest); // 0-based indexing

            if (setU != setV) {
                result.add(edge);
                totalWeight += edge.weight;
                unionSets(parent, rank, setU, setV);
            }
        }

        // Print the edges in the MST
        System.out.println("Edges in the Minimum Spanning Tree:");
        for (Edge edge : result) {
            System.out.println((edge.src + 1) + " -- " + (edge.dest + 1) + " == " + edge.weight); // +1 to adjust for 1-based indexing
        }
        System.out.println("Total weight of the Minimum Spanning Tree: " + totalWeight);
    }

    // Function to add an edge to the graph
    public static void addEdge(List<Edge> graph, int src, int dest, int weight) {
        graph.add(new Edge(src - 1, dest - 1, weight)); // -1 to adjust for 0-based indexing
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V, E; // Number of vertices and edges

        System.out.print("Enter the number of vertices: ");
        V = sc.nextInt();
        System.out.print("Enter the number of edges: ");
        E = sc.nextInt();

        List<Edge> graph = createGraph(V);

        System.out.println("Enter the edges (src dest weight) one per line:");
        for (int i = 0; i < E; ++i) {
            int src, dest, weight;
            src = sc.nextInt();
            dest = sc.nextInt();
            weight = sc.nextInt();
            addEdge(graph, src, dest, weight);
        }

        kruskalMST(V, graph);

        sc.close();
    }
}
