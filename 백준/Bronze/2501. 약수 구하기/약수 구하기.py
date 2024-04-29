# https://www.acmicpc.net/problem/2501
# brute force
import sys

N, K = map(int, sys.stdin.readline().rstrip().split())
divisors = []

for i in range(1, N+1):
    if N%i == 0:
        divisors.append(i)

try:
    print(divisors[K-1])
except:
    print(0)