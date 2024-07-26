import java.util.*;

public class PrimMST {

    static class Edge implements Comparable<Edge> {
        int weight, vertex;

        public Edge(int weight, int vertex) {
            this.weight = weight;
            this.vertex = vertex;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    public static void primMST(int V, List<List<Edge>> graph) {
        int[] minEdge = new int[V];
        int[] parent = new int[V];
        boolean[] inMST = new boolean[V];
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        Arrays.fill(minEdge, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        int startVertex = 0; // Start from an arbitrary vertex, in this case vertex 0
        minEdge[startVertex] = 0;
        pq.add(new Edge(0, startVertex));
        int totalWeight = 0;

        while (!pq.isEmpty()) {
            int u = pq.poll().vertex;

            if (inMST[u]) continue;
            inMST[u] = true;
            totalWeight += minEdge[u];

            for (Edge edge : graph.get(u)) {
                int weight = edge.weight;
                int v = edge.vertex;

                if (!inMST[v] && weight < minEdge[v]) {
                    minEdge[v] = weight;
                    pq.add(new Edge(minEdge[v], v));
                    parent[v] = u;
                }
            }
        }

        System.out.println("Edges in the Minimum Spanning Tree:");
        for (int v = 1; v < V; ++v) {
            if (parent[v] != -1) {
                System.out.println((parent[v] + 1) + " -- " + (v + 1) + " == " + minEdge[v]); // +1 to adjust for 1-based indexing
            }
        }
        System.out.println("Total weight of the Minimum Spanning Tree: " + totalWeight);
    }

    public static void addEdge(List<List<Edge>> graph, int src, int dest, int weight) {
        graph.get(src - 1).add(new Edge(weight, dest - 1)); // -1 to adjust for 0-based indexing
        graph.get(dest - 1).add(new Edge(weight, src - 1)); // -1 to adjust for 0-based indexing
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V, E; // Number of vertices and edges

        System.out.print("Enter the number of vertices: ");
        V = sc.nextInt();
        System.out.print("Enter the number of edges: ");
        E = sc.nextInt();

        List<List<Edge>> graph = new ArrayList<>(V);
        for (int i = 0; i < V; ++i) {
            graph.add(new ArrayList<>());
        }

        System.out.println("Enter the edges (src dest weight) one per line:");
        for (int i = 0; i < E; ++i) {
            int src = sc.nextInt();
            int dest = sc.nextInt();
            int weight = sc.nextInt();
            addEdge(graph, src, dest, weight);
        }

        primMST(V, graph);

        sc.close();
    }
}
