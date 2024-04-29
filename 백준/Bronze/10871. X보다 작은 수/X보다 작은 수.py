import sys

N, X = map(int, sys.stdin.readline().rstrip().split())
series = list(map(int, sys.stdin.readline().rstrip().split()))
result = []
for c in series:
    if int(c) < X:
        result.append(c)

print(*result)