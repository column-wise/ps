# https://www.acmicpc.net/problem/10816
# dictionary
import sys

N = int(sys.stdin.readline().rstrip())
l = list(map(int, sys.stdin.readline().rstrip().split()))
M = int(sys.stdin.readline().rstrip())
target = list(map(int, sys.stdin.readline().rstrip().split()))
d = dict()

for i in l:
    if i in d.keys():
        d[i] += 1
    else:
        d[i] = 1

result = []

for i in target:
    if i in d.keys():
        result.append(d[i])
    else:
        result.append(0)

print(*result)