# https://www.acmicpc.net/problem/2231
# brute force
import sys

N = int(sys.stdin.readline().rstrip())

result = 0
for i in range(N):
    
    d = i
    share = i//10
    rest = i%10

    while share > 0:
        d += rest
        rest = share%10
        share = share//10
    
    d += rest

    if d == N:
        result = i
        break

print(result)