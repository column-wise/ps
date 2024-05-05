# https://www.acmicpc.net/problem/1388
#
import sys

N, M = map(int, sys.stdin.readline().rstrip().split())
matrix = []
for i in range(N):
    matrix.append(list(sys.stdin.readline().rstrip()))

count = 0
for i in range(N):
    last = ''
    for j in range(M):
        if matrix[i][j] == '-':
            if last == '-':
                continue
            else:
                last = '-'
                count += 1
        else:
            last = '|'

for i in range(M):
    last = ''
    for j in range(N):
        if matrix[j][i] == '|':
            if last == '|':
                continue
            else:
                last = '|'
                count += 1
        else:
            last = '-'

print(count)