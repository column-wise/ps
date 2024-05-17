# https://www.acmicpc.net/problem/11660
# prefix sum
import sys

N, M = map(int, sys.stdin.readline().rstrip().split())
matrix = []
for i in range(N):
    matrix.append(list(map(int, sys.stdin.readline().rstrip().split())))

prefix_sum = [[0 for _ in range(N+1)] for _ in range(N+1)]

for i in range(1,N+1):
    for j in range(1,N+1):
        prefix_sum[i][j] = matrix[i-1][j-1] + prefix_sum[i][j-1] + prefix_sum[i-1][j] - prefix_sum[i-1][j-1]

for i in range(M):
    x1, y1, x2, y2 = map(int, sys.stdin.readline().rstrip().split())
    print(prefix_sum[x2][y2] - prefix_sum[x1-1][y2] - prefix_sum[x2][y1-1] + prefix_sum[x1-1][y1-1])