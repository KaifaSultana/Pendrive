import java.util.*;

class FordFulkerson {
    static final int V = 6; // Number of vertices in the given graph

    // BFS to find if there is a path from source to sink in the residual graph
    boolean bfs(int rGraph[][], int s, int t, int parent[]) {
        boolean visited[] = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        parent[s] = -1;

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v = 0; v < V; v++) {
                if (!visited[v] && rGraph[u][v] > 0) {
                    if (v == t) {
                        parent[v] = u;
                        return true;
                    }
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }
        return false;
    }

    // Implementing Ford-Fulkerson algorithm to find maximum flow
    int fordFulkerson(int graph[][], int s, int t) {
        int u, v;
        int rGraph[][] = new int[V][V];

        for (u = 0; u < V; u++)
            for (v = 0; v < V; v++)
                rGraph[u][v] = graph[u][v];

        int parent[] = new int[V];
        int maxFlow = 0;

        // Augment the flow while there is a path from source to sink
        while (bfs(rGraph, s, t, parent)) {
            // Find the maximum flow through the path found
            int pathFlow = Integer.MAX_VALUE;
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                pathFlow = Math.min(pathFlow, rGraph[u][v]);
            }

            // update residual capacities of the edges and reverse edges along the path
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] -= pathFlow;
                rGraph[v][u] += pathFlow;
            }

            // Add path flow to overall flow
            maxFlow += pathFlow;
        }

        return maxFlow;
    }

    public static void main(String[] args) throws java.lang.Exception {
        Scanner scanner = new Scanner(System.in);

        // Prompt user to enter the number of vertices
        System.out.print("Enter the number of vertices: ");
        int V = scanner.nextInt();
        
        int graph[][] = new int[V][V];

        // Prompt user to enter the adjacency matrix of the graph
        System.out.println("Enter the adjacency matrix of the graph:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }

        // Prompt user to enter source and sink vertices
        System.out.print("Enter source vertex: ");
        int source = scanner.nextInt();
        System.out.print("Enter sink vertex: ");
        int sink = scanner.nextInt();

        FordFulkerson m = new FordFulkerson();

        // Calculate maximum flow using Ford-Fulkerson algorithm
        System.out.println("The maximum possible flow is " + m.fordFulkerson(graph, source, sink));

        scanner.close();
    }
}
