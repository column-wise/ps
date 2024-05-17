# https://www.acmicpc.net/problem/2003
# prefix sum
import sys

N, M = map(int, sys.stdin.readline().rstrip().split())
sequence = list(map(int, sys.stdin.readline().rstrip().split()))
prefix_sum = [0]

for i in range(N):
    prefix_sum.append(prefix_sum[i]+sequence[i])

count = 0

for i in range(N+1):
    end = i
    start = 0
    while start<end:
        if prefix_sum[end]-prefix_sum[start] == M:
            count += 1
        
        start += 1

print(count)