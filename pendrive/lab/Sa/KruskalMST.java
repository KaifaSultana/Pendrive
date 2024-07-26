import java.util.*;

class Edge implements Comparable<Edge> {
    int src, dest, weight;

    Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }
}

public class KruskalMST {
    static int find(int[] parent, int i) {
        if (parent[i] != i) {
            parent[i] = find(parent, parent[i]);
        }
        return parent[i];
    }

    static void union(int[] parent, int[] rank, int x, int y) {
        int rootX = find(parent, x);
        int rootY = find(parent, y);

        if (rootX != rootY) {
            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }

    
    static void kruskalMST(int V, List<Edge> graph) {
        List<Edge> result = new ArrayList<>(); 
        int totalWeight = 0; 

        
        Collections.sort(graph);

        int[] parent = new int[V];
        int[] rank = new int[V];

        for (int v = 0; v < V; ++v) {
            parent[v] = v;
            rank[v] = 0;
        }

        for (Edge edge : graph) {
            int setU = find(parent, edge.src);
            int setV = find(parent, edge.dest);

            if (setU != setV) {
                result.add(edge);
                totalWeight += edge.weight;
                union(parent, rank, setU, setV);
            }
        }

        System.out.println("Edges in the Minimum Spanning Tree:");
        for (Edge edge : result) {
            System.out.println((edge.src + 1) + " -- " + (edge.dest + 1) + " == " + edge.weight); 
        }
        System.out.println("Total weight of the Minimum Spanning Tree: " + totalWeight);
    }

    static void addEdge(List<Edge> graph, int src, int dest, int weight) {
        graph.add(new Edge(src - 1, dest - 1, weight)); 
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int V = scanner.nextInt();

        System.out.print("Enter the number of edges: ");
        int E = scanner.nextInt();

        List<Edge> graph = new ArrayList<>();

        System.out.println("Enter the edges (src dest weight) one per line:");
        for (int i = 0; i < E; ++i) {
            int src = scanner.nextInt();
            int dest = scanner.nextInt();
            int weight = scanner.nextInt();
            addEdge(graph, src, dest, weight);
        }

        kruskalMST(V, graph);

        scanner.close();
    }
}

