# https://www.acmicpc.net/problem/10814
# sorting
import sys

N = int(sys.stdin.readline().rstrip())
l = []
for i in range(N):
    a, n = sys.stdin.readline().rstrip().split()
    a = int(a)
    l.append((a, n))

l.sort(key=lambda x:x[0])

for i in range(N):
    print(l[i][0], l[i][1])