# https://www.acmicpc.net/problem/2178
# BFS
import sys
from collections import deque

dx = [0,1,0,-1]
dy = [1,0,-1,0]

def BFS(maze):
    queue = deque()
    queue.append([0,0,1])
    visited = [[False for i in range(len(maze[0]))] for j in range(len(maze))]

    while queue:
        x, y, count = queue.popleft()
        visited[x][y] = True

        if x == len(maze)-1 and y == len(maze[0])-1:
            return count

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if 0 <= nx < len(maze) and 0 <= ny <len(maze[0]) and not visited[nx][ny] and maze[nx][ny] == 1:
                visited[nx][ny] = True
                queue.append([nx, ny, count+1])


N, M = map(int, sys.stdin.readline().rstrip().split())
maze = []

for i in range(N):
    maze.append(list(map(int, sys.stdin.readline().rstrip())))

print(BFS(maze))