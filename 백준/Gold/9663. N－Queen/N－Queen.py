# https://www.acmicpc.net/problem/9663
# back tracking
import sys

count = 0

def BT(board, N, start):
    if len(board) == 0:
        board.append(start)
        
    if len(board) == N:
        global count
        count += 1
        return
    else:
        for i in range(N):
            if i in board:
                continue
            for j in range(len(board)):
                if len(board)-j == abs(board[j]-i):
                    break
            else:
                board.append(i)
                BT(board, N, start)
                board.pop()
            
            continue   

N = int(sys.stdin.readline().rstrip())
board = [-1 for _ in range(N)]

for i in range(N):
    BT([], N, i)

print(count)