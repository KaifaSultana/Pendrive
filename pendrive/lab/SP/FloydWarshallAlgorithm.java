import java.util.Scanner;

public class FloydWarshallAlgorithm {

    private static final int INF = Integer.MAX_VALUE;

    // Function to perform Floyd-Warshall algorithm
    public void floydWarshall(int[][] graph, int V) {
        int[][] dist = new int[V][V];

        // Initialize distance matrix same as input graph matrix
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dist[i][j] = graph[i][j];
            }
        }

        // Main algorithm implementation
        for (int k = 0; k < V; k++) {
            // Pick all vertices as source one by one
            for (int i = 0; i < V; i++) {
                // Pick all vertices as destination for the above picked source
                for (int j = 0; j < V; j++) {
                    // If vertex k is on the shortest path from i to j,
                    // then update the value of dist[i][j]
                    if (dist[i][k] != INF && dist[k][j] != INF &&
                            dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // Print the shortest distances
        printSolution(dist, V);
    }

    // Function to print the solution
    public void printSolution(int[][] dist, int V) {
        System.out.println("The shortest distances between every pair of vertices:");
        for (int i = 0; i < V; ++i) {
            for (int j = 0; j < V; ++j) {
                if (dist[i][j] == INF) {
                    System.out.print("INF ");
                } else {
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int V = scanner.nextInt();

        int[][] graph = new int[V][V];

        System.out.println("Enter the adjacency matrix (use 'INF' for infinity):");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                String input = scanner.next();
                if (input.equals("INF")) {
                    graph[i][j] = INF;
                } else {
                    graph[i][j] = Integer.parseInt(input);
                }
            }
        }

        FloydWarshallAlgorithm fw = new FloydWarshallAlgorithm();

        // Print the solution
        fw.floydWarshall(graph, V);

        scanner.close();
    }
}
