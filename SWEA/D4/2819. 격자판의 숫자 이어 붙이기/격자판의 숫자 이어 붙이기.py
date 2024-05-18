T = int(input())
for test_case in range(1, T + 1):
    result = []
    dx = [0,1,0,-1]
    dy = [1,0,-1,0]

    def DFS(board, x, y, visited, sequence):
        if 0<=x<4 and 0<=y<4:
            sequence += str(board[x][y])
            if len(sequence) == 7:
                result.append(sequence)
            else:
                for i in range(4):
                    DFS(board, x+dx[i], y+dy[i], visited, sequence)


    board = []
    for _ in range(4):
        board.append(list(map(int, input().split())))

    visited = [[False for _ in range(4)] for _ in range(4)]

    for i in range(4):
        for j in range(4):
            DFS(board, i, j, visited, '')
    result = set(result)
    print(f"#{test_case} {len(result)}")