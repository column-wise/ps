# https://www.acmicpc.net/problem/2609
# math
import sys

N, M = map(int, sys.stdin.readline().rstrip().split())

if N>M:
    dividend = N
    divisor = M
else:
    dividend = M
    divisor = N

r = dividend%divisor

while r != 0:
    dividend = divisor
    divisor = r
    r = dividend%divisor

print(divisor)
print(N*M//divisor)