# https://www.acmicpc.net/problem/2775
# DP
import sys
dptable = [[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15]]
for i in range(1,15):
    dptable.append([1])
    for j in range(1, 15):
        dptable[i].append(dptable[i][j-1]+dptable[i-1][j])

T = int(sys.stdin.readline().rstrip())
for _ in range(T):
    k = int(sys.stdin.readline().rstrip())
    n = int(sys.stdin.readline().rstrip())

    print(dptable[k][n-1])