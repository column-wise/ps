# https://www.acmicpc.net/problem/17505
import sys

N, K = map(int, sys.stdin.readline().rstrip().split())

series = [0] * N

while K > 0:
    if N-1 <= K:
        series[len(series)-N] = N
        K -= (N-1)
        N -= 1
    else:
        series[len(series)-K-1] = N
        K = 0
        N -= 1

for i in range(len(series)-1, -1, -1):
    if series[i] == 0:
        series[i] = N
        N -= 1

print(' '.join(map(str, series)))