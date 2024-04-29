# https://www.acmicpc.net/problem/4619
# brute force
import sys

while True:
    B, N = map(int, sys.stdin.readline().rstrip().split())
    if B == 0 and N == 0:
        break
    a = 0
    while a**N < B:
        a += 1
    
    if a**N - B < B - (a-1)**N:
        print(a)
    else:
        print(a-1)