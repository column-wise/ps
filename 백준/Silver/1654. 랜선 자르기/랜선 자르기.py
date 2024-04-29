# https://www.acmicpc.net/problem/1654
# greedy?
import sys

K, N = map(int, sys.stdin.readline().rstrip().split())
have = []
for i in range(K):
    have.append(int(sys.stdin.readline().rstrip()))

u = max(have)
l = 1
count = 0

while u >= l:
    lan = (u+l)//2
    count = 0
    for i in have:
        count += i//lan

    if count >= N:
        l = lan+1
    elif count < N:
        u = lan-1
    
print(u)