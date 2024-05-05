# https://www.acmicpc.net/problem/2606
# DFS
import sys

def DFS(net, visited, n):
    if visited[n] == True:
        return
    else:
        visited[n] = True
        for i in net[n]:
            DFS(net, visited, i)
        


N = int(sys.stdin.readline().rstrip())
K = int(sys.stdin.readline().rstrip())
net = [[] for i in range(N)]

for i in range(K):
    a, b = map(int, sys.stdin.readline().rstrip().split())
    net[a-1].append(b-1)
    net[b-1].append(a-1)


visited = [False] * N
DFS(net, visited, 0)

result = visited.count(True)-1
print(result)