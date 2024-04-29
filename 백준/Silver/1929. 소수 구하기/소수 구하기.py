# https://www.acmicpc.net/problem/1929
# math
import sys
M, N = map(int, sys.stdin.readline().rstrip().split())

a = [False, False] + [True]*(N-1)

for i in range(2,N+1):
    if a[i]:
        for j in range(2*i, N+1, i):
            a[j] = False

for i in range(M, N+1):
    if a[i]:
        print(i)