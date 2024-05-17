# https://www.acmicpc.net/problem/1806
# prefix sum
import sys

N, S = map(int, sys.stdin.readline().rstrip().split())
sequence = list(map(int, sys.stdin.readline().rstrip().split()))

prefix_sum = [0]
for i in range(N):
    prefix_sum.append(prefix_sum[i]+sequence[i])

start = 0
end = 0
result = []

for i in range(N+1):
    end = i
    while start<end:
        diff = prefix_sum[end]-prefix_sum[start]
        if diff>=S:
            result.append(end-start)
            start+=1
        else:
            break

if result == []:
    print(0)
else:
    print(min(result))