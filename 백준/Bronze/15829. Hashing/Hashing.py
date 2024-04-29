# https://www.acmicpc.net/problem/15829
# implementation
import sys
L = int(sys.stdin.readline().rstrip())
l = sys.stdin.readline().rstrip()
sum = 0
r = 1
M = 1234567891

for i in range(L):
    sum += (ord(l[i]) - 96)*r
    r *= 31

print(sum%M)