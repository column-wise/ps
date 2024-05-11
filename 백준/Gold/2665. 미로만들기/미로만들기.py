# https://www.acmicpc.net/problem/2665
# BFS
import sys
from collections import deque

dx = [0,1,0,-1]
dy = [1,0,-1,0]

def BFS(rooms):
    queue = deque()
    queue.append((0,0))
    visited = [[10e8 for i in range(N)] for j in range(N)]

    if rooms[0][0] == 1:
        visited[0][0] = 0
    else:
        visited[0][0] = 1

    while queue:
        x, y = queue.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if nx < 0 or ny < 0 or nx>=len(rooms) or ny>=len(rooms[0]):
                continue
            else:
                if visited[nx][ny] > visited[x][y]:
                    queue.append((nx, ny))

                if rooms[nx][ny] == 1:
                    visited[nx][ny] = min(visited[nx][ny], visited[x][y])

                else:
                    visited[nx][ny] = min(visited[nx][ny], visited[x][y] + 1)
                    
    return visited[len(visited)-1][len(visited[0])-1]

            


N = int(sys.stdin.readline().rstrip())
rooms = []

for i in range(N):
    rooms.append(list(map(int,sys.stdin.readline().rstrip())))

print(BFS(rooms))