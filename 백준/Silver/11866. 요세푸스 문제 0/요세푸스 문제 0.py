# https://www.acmicpc.net/problem/11866
# math
import sys

N, K = map(int, sys.stdin.readline().rstrip().split())
circle = [i+1 for i in range(N)]
index = K-1
result = []

while circle:
    index = index%(len(circle))
    result.append(circle[index])
    del circle[index]
    index += (K-1)

print("<", end='')
print(*result,sep=', ',end='')
print(">")