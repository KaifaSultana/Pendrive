INF = float('inf')
def floyd_warshall(graph):
    V = len(graph)
    
    dist = [[INF] * V for _ in range(V)]
    pred = [[None] * V for _ in range(V)]
    
    for i in range(V):
        for j in range(V):
            if i == j:
                dist[i][j] = 0
            elif graph[i][j] != INF:
                dist[i][j] = graph[i][j]
                pred[i][j] = i
    
    for k in range(V):
        for i in range(V):
            for j in range(V):
                if dist[i][k] + dist[k][j] < dist[i][j]:
                    dist[i][j] = dist[i][k] + dist[k][j]
                    pred[i][j] = pred[k][j]
    
    return dist, pred
def print_path(pred, u, v):
    path = []
    while v is not None:
        path.insert(0, v)
        v = pred[u][v]
    return path

graph = [
    [0, 3, INF, 7],
    [8, 0, 2, INF],
    [5, INF, 0, 1],
    [2, INF, INF, 0]
]

dist, pred = floyd_warshall(graph)

print("Distance Matrix:")
for row in dist:
    print(row)

print("\nPredecessor Matrix:")
for row in pred:
    print(row)

u, v = 1, 3
path = print_path(pred, u, v)
print(f"\nShortest path from {u} to {v}: {path}")