# https://www.acmicpc.net/problem/1018
# brute force
import sys

BW = ['B','W']

N, M = map(int, sys.stdin.readline().rstrip().split())
board = []

for i in range(N):
    board.append(sys.stdin.readline().rstrip())
    
result = 64

for i in range(N-7):
    for j in range(M-7):
        for k in range(2):
            count = 0
            for m in range(8):
                for n in range(8):
                    if board[i+m][j+n] != BW[(m+n+k)%2]:
                        count += 1
            result = min(result,count)

print(result)