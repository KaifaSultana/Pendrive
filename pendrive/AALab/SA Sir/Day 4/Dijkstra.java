import java.util.*;

public class Dijkstra {
    private int V; // number of vertices
    private int[][] adjMatrix; // adjacency matrix
    private int[] d; // distance array
    private Set<Integer> S; // set of vertices whose shortest path from u has been finalized
    private Map<Integer, Character> vertexMap; // map to store vertex labels

    // Constructor
    public Dijkstra(int v) {
        V = v;
        adjMatrix = new int[v][v];
        d = new int[v];
        S = new HashSet<>();
        vertexMap = new HashMap<>();

        for (int i = 0; i < v; ++i) {
            Arrays.fill(adjMatrix[i], Integer.MAX_VALUE);
        }
    }

    // Function to add an edge into the graph
    public void addEdge(int u, int v, int weight) {
        adjMatrix[u][v] = weight;
        adjMatrix[v][u] = weight; // For undirected graph
    }

    // Function to implement Dijkstra's algorithm
    public void dijkstra(int src) {
        Arrays.fill(d, Integer.MAX_VALUE);
        d[src] = 0;

        S.add(src);

        // Initialize d[] with weights of edges from src
        for (int v = 0; v < V; v++) {
            if (v != src && adjMatrix[src][v] != Integer.MAX_VALUE) {
                d[v] = adjMatrix[src][v];
            }
        }

        while (S.size() != V) {
            // Choose the node y in V - S with the minimum distance
            int u = minDistance();
            if (u == -1) break;

            // Add y to S
            S.add(u);

            // Update the distance value of the adjacent vertices of the chosen vertex
            for (int v = 0; v < V; v++) {
                if (!S.contains(v) && adjMatrix[u][v] != Integer.MAX_VALUE && d[u] != Integer.MAX_VALUE
                        && d[u] + adjMatrix[u][v] < d[v]) {
                    d[v] = d[u] + adjMatrix[u][v];
                }
            }
        }

        // Print the constructed distance array
        printSolution();
    }

    // A utility function to find the vertex with minimum distance value from the set of vertices not yet processed
    private int minDistance() {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < V; v++) {
            if (!S.contains(v) && d[v] < min) {
                min = d[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    // A utility function to print the constructed distance array
    private void printSolution() {
        System.out.println("Vertex \t Distance from Source");
        for (int i = 0; i < V; i++) {
            System.out.println(vertexMap.get(i) + "\t " + (d[i] == Integer.MAX_VALUE ? "INF" : d[i]));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int V = scanner.nextInt();

        Dijkstra g = new Dijkstra(V);

        // Initialize vertex map
        for (int i = 0; i < V; i++) {
            System.out.print("Enter label for vertex " + i + ": ");
            char label = scanner.next().charAt(0);
            g.vertexMap.put(i, label);
        }

        System.out.print("Enter the number of edges: ");
        int E = scanner.nextInt();

        System.out.println("Enter the edges (format: u v weight for edge u<->v with weight): ");
        for (int i = 0; i < E; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int weight = scanner.nextInt();
            g.addEdge(u, v, weight);
        }

        System.out.print("Enter the starting vertex (0 to " + (V - 1) + "): ");
        int startVertex = scanner.nextInt();

        System.out.println("Following is Dijkstra's shortest path algorithm (starting from vertex " + g.vertexMap.get(startVertex) + "):");
        g.dijkstra(startVertex);

        scanner.close();
    }
}
