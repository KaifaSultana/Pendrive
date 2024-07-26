import java.util.Scanner;

public class FloydWarshall {

    static final int INF = 99999;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int V = scanner.nextInt();

        int[][] graph = new int[V][V];

        System.out.println("Enter the adjacency matrix (INF for infinity):");
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

        floydWarshall(graph, V);
    }

    static void floydWarshall(int[][] dist, int V) {
        int[][] pi = new int[V][V];

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (dist[i][j] != INF && i != j) {
                    pi[i][j] = i;
                } else {
                    pi[i][j] = -1;
                }
            }
        }

        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j] && dist[i][k] != INF && dist[k][j] != INF) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        pi[i][j] = pi[k][j];
                    }
                }
            }
        }

        printSolution(dist, pi, V);
    }

    static void printSolution(int[][] dist, int[][] pi, int V) {
        System.out.println("The shortest distances between every pair of vertices:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (dist[i][j] == INF)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j] + "  ");
            }
            System.out.println();
        }

        System.out.println("\nThe intermediate vertices for the shortest paths:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (pi[i][j] == -1)
                    System.out.print("NIL ");
                else
                    System.out.print((pi[i][j] + 1) + "   ");
            }
            System.out.println();
        }
    }
}

