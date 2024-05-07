# https://www.acmicpc.net/problem/2667
# DFS
import sys
dx = [0,1,0,-1]
dy = [1,0,-1,0]

def DFS(map, x, y, complex, index):
    if x<0 or y<0 or x>=len(map) or y>=len(map[0]) or visited[x][y] or map[x][y] == '0':
        return
    else:
        visited[x][y] = True
        if map[x][y] == '1':
            complex[x][y] = index
        for i in range(4):
            DFS(map, x+dx[i], y+dy[i], complex, index)

N = int(sys.stdin.readline().rstrip())
map = []
for _ in range(N):
    map.append(list(sys.stdin.readline().rstrip()))

visited = [[False for _ in range(N)] for _ in range(N)]

complex = [[0 for _ in range(N)] for _ in range(N)]
index = 1


for i in range(N):
    for j in range(N):
        DFS(map, i, j, complex, index)
        index += 1

count = dict()
for i in range(N):
    for j in range(N):
        if complex[i][j] != 0:
            if complex[i][j] in count.keys():
                count[complex[i][j]].append((i,j))
            else:
                count[complex[i][j]] = []
                count[complex[i][j]].append((i,j))

print(len(count.keys()))
c = list(count.values())
result = []
for i in range(len(c)):
    result.append(len(c[i]))

result.sort()
for i in range(len(result)):
    print(result[i])