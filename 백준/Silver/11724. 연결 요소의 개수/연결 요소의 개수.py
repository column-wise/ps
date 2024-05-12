# https://www.acmicpc.net/problem/11724
# DFS
import sys
sys.setrecursionlimit(100000)

def DFS(v, visited, group, group_n):
    if visited[v] == True:
        return
    else:
        visited[v] = True
        group[group_n].append(v)
        for n in graph[v]:
            group[group_n].append(n)
            DFS(n, visited, group, group_n)

N, M = map(int, sys.stdin.readline().rstrip().split())
graph = [[] for _ in range(N+1)]
for i in range(M):
    a, b = map(int, sys.stdin.readline().rstrip().split())
    graph[a].append(b)
    graph[b].append(a)

visited = [False for i in range(N+1)]
group = [[] for i in range(N+1)]
group_n = 1
for i in range(1, N+1):
    DFS(i, visited, group, group_n)
    group_n += 1
count = 0
for i in range(N+1):
    if group[i] != []:
        count += 1

print(count)