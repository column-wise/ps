# https://www.acmicpc.net/problem/10773
# data structure
import sys
K = int(sys.stdin.readline().rstrip())
sum = 0
l = []
for i in range(K):
    n = int(sys.stdin.readline().rstrip())
    if n == 0:
        l.pop()
    else:
        l.append(n)

for i in l:
    sum += i

print(sum)