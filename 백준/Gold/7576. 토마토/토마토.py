# https://www.acmicpc.net/problem/7576
# BFS
import sys
from collections import deque

dx = [0,1,0,-1]
dy = [1,0,-1,0]

def BFS(box, queue, result):
    while queue:
        x, y, time = queue.popleft()
        result.append(time)
        time += 1
        for i in range(4):
            nx = x+dx[i]
            ny = y+dy[i]
            if 0<=nx<len(box) and 0<=ny<len(box[0]) and visited[nx][ny] == False and box[nx][ny] != -1:
                queue.append((nx,ny,time))
                box[nx][ny] += 1
                visited[nx][ny] = True

M, N = map(int, sys.stdin.readline().rstrip().split())
box = []
for i in range(N):
    box.append(list(map(int, sys.stdin.readline().rstrip().split())))

tomato = []
visited = [[False for i in range(M)] for j in range(N)]
for i in range(N):
    for j in range(M):
        if box[i][j] == 1:
            tomato.append((i,j,0))
            visited[i][j] = True

queue = deque(tomato)
result = []
BFS(box, queue, result)
isPossible = True
for i in range(N):
    for j in range(M):
        if box[i][j] == 0:
            isPossible = False
            break

if isPossible:
    result.sort(reverse=True)
    print(result[0])
else:
    print(-1)