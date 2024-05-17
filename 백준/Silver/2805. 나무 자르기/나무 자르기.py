# https://www.acmicpc.net/problem/2805
# binary search
import sys

N, M = map(int, sys.stdin.readline().rstrip().split())
tree = list(map(int, sys.stdin.readline().rstrip().split()))
lower = 0
upper = max(tree)

while lower <= upper:
    result = 0
    m = (lower+upper)//2

    for t in tree:
        diff = 0 if t<m else t-m
        result += diff
    if result >= M:
        lower = m+1
    else:
        upper = m-1

print(upper)