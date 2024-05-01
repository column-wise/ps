# https://www.acmicpc.net/problem/11726
# DP
import sys

dptable = [0,1,2,3] + [0] * 998
for i in range(4,1002):
    dptable[i] = dptable[i-2] + dptable[i-1]
    dptable[i] %= 10007

n = int(sys.stdin.readline().rstrip())
print(dptable[n])