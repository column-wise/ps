# https://www.acmicpc.net/problem/4796
import sys

i = 1
while True:
    L, P, V = map(int, sys.stdin.readline().rstrip().split())
    if L == 0 and P == 0 and V == 0:
        break
    else:
        can_use = 0
        current = 0
        next = P
        while next < V:
            can_use += L
            next += P
            current += P
        can_use += min(L, V-current)
        print("Case "+ str(i)+':', can_use)
        i += 1