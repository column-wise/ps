import sys
from collections import deque

def BFS(graph, start, target, kevin):
    queue = deque()
    queue.append((start,0))
    visited = [False for _ in range(len(graph))]
    visited[start] = True

    while queue:
        people, count = queue.popleft()
        if people == target:
            kevin[people] += count
            return
        for f in graph[people]:
            if not visited[f]:
                queue.append((f,count+1))
                visited[f] = True
        visited[f] = False



N, M = map(int, sys.stdin.readline().rstrip().split())
graph = [[] for _ in range(N)]
kevin = [0 for _ in range(N)]
for i in range(M):
    a, b = map(int, sys.stdin.readline().rstrip().split())
    graph[a-1].append(b-1)
    graph[b-1].append(a-1)

for i in range(N):
    for j in range(N):
        BFS(graph, i, j, kevin)

print(kevin.index(min(kevin))+1)