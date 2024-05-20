import sys
from collections import deque

def BFS(start, target):
    queue = deque()
    limit = max(start, target*2)
    time = 0
    queue.append((start,time))
    visited = [False for _ in range(limit+1)]

    while queue:
        n,time = queue.popleft()
        if n == target:
            print(time)
            break
        else:
            time += 1
            if 2*n < limit and not visited[2*n]:
                queue.append((2*n,time))
                visited[2*n] = True

            if n+1 < limit and not visited[n+1]:
                queue.append((n+1,time))
                visited[n+1] = True
                
            if 0 <= n-1 < limit and not visited[n-1]:
                queue.append((n-1,time))
                visited[n-1] = True
            
            


N, K = map(int, sys.stdin.readline().rstrip().split())
BFS(N,K)