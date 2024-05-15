import sys

count = 0

def is_promising(board, row, col):
    for i in range(row):
        if board[i] == col or board[i] - i == col - row or board[i] + i == col + row:
            return False
    return True

def BT(board, N, row):
    global count
    if row == N:
        count += 1
        return
    
    for col in range(N):
        if is_promising(board, row, col):
            board[row] = col
            BT(board, N, row+1)
            board[row] = -1


N = int(sys.stdin.readline().rstrip())
board = [-1 for _ in range(N)]


BT(board, N, 0)
print(count)