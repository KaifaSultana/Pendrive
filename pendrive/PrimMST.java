import java.util.*;

// Pair class to represent edges (weight, vertex)
class Edge implements Comparable<Edge> {
    int weight, vertex;

    Edge(int weight, int vertex) {
        this.weight = weight;
        this.vertex = vertex;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }
}

public class PrimMST {

    static void primMST(int V, List<List<Edge>> graph) {
        int[] minEdge = new int[V];
        int[] parent = new int[V];
        boolean[] inMST = new boolean[V];
        Arrays.fill(minEdge, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int startVertex = 0; // Start from vertex 0
        minEdge[startVertex] = 0;
        pq.offer(new Edge(0, startVertex));
        int totalWeight = 0;

        while (!pq.isEmpty()) {
            Edge minEdgePair = pq.poll();
            int u = minEdgePair.vertex;

            if (inMST[u]) continue;
            inMST[u] = true;
            totalWeight += minEdgePair.weight;

            for (Edge edge : graph.get(u)) {
                int weight = edge.weight;
                int v = edge.vertex;

                if (!inMST[v] && weight < minEdge[v]) {
                    minEdge[v] = weight;
                    pq.offer(new Edge(weight, v));
                    parent[v] = u;
                }
            }
        }

        System.out.println("Edges in the Minimum Spanning Tree:");
        for (int v = 1; v < V; ++v) {
            if (parent[v] != -1) {
                System.out.println((parent[v] + 1) + " -- " + (v + 1) + " == " + minEdge[v]);
            }
        }
        System.out.println("Total weight of the Minimum Spanning Tree: " + totalWeight);
    }

    static void addEdge(List<List<Edge>> graph, int src, int dest, int weight) {
        graph.get(src - 1).add(new Edge(weight, dest - 1)); // Adjust for 0-based indexing
        graph.get(dest - 1).add(new Edge(weight, src - 1)); // Adjust for 0-based indexing
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int V = scanner.nextInt();
        System.out.print("Enter the number of edges: ");
        int E = scanner.nextInt();

        List<List<Edge>> graph = new ArrayList<>(V);
        for (int i = 0; i < V; ++i) {
            graph.add(new ArrayList<>());
        }

        System.out.println("Enter the edges (src dest weight) one per line:");
        for (int i = 0; i < E; ++i) {
            int src = scanner.nextInt();
            int dest = scanner.nextInt();
            int weight = scanner.nextInt();
            addEdge(graph, src, dest, weight);
        }

        primMST(V, graph);

        scanner.close();
    }
}
