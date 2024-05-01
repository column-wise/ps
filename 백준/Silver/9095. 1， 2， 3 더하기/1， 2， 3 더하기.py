# https://www.acmicpc.net/problem/9095
# DP
import sys
DPtable = [0] * 12
DPtable[1] = 1
DPtable[2] = 2
DPtable[3] = 4
for i in range(4, 12):
    DPtable[i] = DPtable[i-3] + DPtable[i-2] + DPtable[i-1]

T = int(sys.stdin.readline().rstrip())
for _ in range(T):
    n = int(sys.stdin.readline().rstrip())
    print(DPtable[n])