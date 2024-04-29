# https://www.acmicpc.net/problem/10989
# sorting
import sys

N = int(sys.stdin.readline().rstrip())
count = [0] * 10001

for _ in range(N):
    count[int(sys.stdin.readline().rstrip())] += 1

for i in range(10001):
    if count[i] > 0:
        for j in range(count[i]):
            print(str(i))