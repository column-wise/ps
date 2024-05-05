# https://www.acmicpc.net/problem/1260
# DFS / BFS
import sys
from collections import deque

def DFS(net, visited , n):
    if visited[n]:
        return
    else:
        visited[n] = True
        print(n,end=' ')
        for r in net[n]:
            DFS(net, visited, r)

def BFS(net, visited, queue, n):
    if visited[n]:
        return
    else:
        queue.append(n)
        visited[n] = True
        while queue:
            n = queue.popleft()
            print(n, end=' ')
            for r in net[n]:
                if visited[r] == False:
                    queue.append(r)
                    visited[r] = True



N, M, V = map(int, sys.stdin.readline().rstrip().split())
net = [[] for _ in range(N+1)]

for i in range(M):
    a, b = map(int, sys.stdin.readline().rstrip().split())
    net[a].append(b)
    net[b].append(a)

for i in range(len(net)):
    net[i].sort()

visited = [False] * (N+1)

DFS(net, visited, V)
print()

visited = [False] * (N+1)
queue = deque()
BFS(net, visited, queue, V)