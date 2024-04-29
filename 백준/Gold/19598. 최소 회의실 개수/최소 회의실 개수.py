# https://www.acmicpc.net/problem/19598
import sys
from heapq import heappush, heappop

N = int(sys.stdin.readline().rstrip())
conferences = []
using = []
count = 0

for i in range(N):
    s, e = map(int, sys.stdin.readline().rstrip().split())
    conferences.append((s, e))

conferences.sort(key = lambda x : x[0])
now = conferences[0][0]

for s, e in conferences:
    if using and using[0] <= s:
        heappop(using)
    heappush(using, e)

print(len(using))