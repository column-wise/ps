T = int(input())
for test_case in range(1, T + 1):
    dx = [0,1,0,-1,1,1,-1,-1]
    dy = [1,0,-1,0,-1,1,1,-1]

    N, M = map(int, input().split())
    board = [[0 for _ in range(N)] for _ in range(N)]

    # 1 => B / 2=> W
    board[N//2][(N//2)-1] = 1
    board[(N//2)-1][N//2] = 1
    board[(N//2)-1][(N//2)-1] = 2
    board[N//2][N//2] = 2

    for i in range(M):
        x, y, c = map(int, input().split())
        x-=1
        y-=1
        board[x][y] = c
        for j in range(8):
            nx = x+dx[j]
            ny = y+dy[j]
            if nx<0 or ny<0 or nx>=N or ny>=N:
                continue
            count = 0
            if board[nx][ny] != 0 and board[nx][ny] != c:
                while 0<=nx<N and 0<=ny<N:
                    if board[nx][ny] != c and board[nx][ny] != 0:
                        nx += dx[j]
                        ny += dy[j]
                        count += 1
                    elif board[nx][ny] == c:
                        nx = x+dx[j]
                        ny = y+dy[j]
                        for k in range(count):
                            board[nx][ny] = c
                            nx += dx[j]
                            ny += dy[j]
                        break
                    else:
                        break

    w_count = 0
    b_count = 0
    for i in range(N):
        for j in range(N):
            if board[i][j] == 1:
                b_count += 1
            elif board[i][j] == 2:
                w_count += 1

    print(f"#{test_case} {b_count} {w_count}")
