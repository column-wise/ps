from collections import deque
dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]

def BFS(graph, a, b):
    queue = deque()
    queue.append((a,b))
    graph[a][b] = 0

    while queue:
        x, y = queue.popleft()
        for i in range(4):
            nx = x + dx[i];
            ny = y + dy[i];
            if nx < 0 or nx >= N or ny < 0 or ny >= M:
                continue

            if s[nx][ny] == 1:
                s[nx][ny] = 0
                queue.append((nx, ny))

    return

T = int(input())

for t in range(T):

    N, M, K = map(int, input().split())

    if 1<=N<=50 and 1<=M<=50 and 1<=K<=2500:
        s = [[0] * M for i in range(N)]
        count = 0

        for k in range(K):
            X, Y = map(int, input().split())
            s[X][Y] = 1
    else:
        break

    for i in range(N):
        for j in range(M):
            if s[i][j] == 1:
                BFS(s, i, j)
                count += 1

            else:
                continue

    print(count)