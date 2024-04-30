# https://www.acmicpc.net/problem/5532
# math
import sys
import math

L = int(sys.stdin.readline().rstrip())
A = int(sys.stdin.readline().rstrip())
B = int(sys.stdin.readline().rstrip())
C = int(sys.stdin.readline().rstrip())
D = int(sys.stdin.readline().rstrip())

print(L-max(math.ceil(A/C),math.ceil(B/D)))