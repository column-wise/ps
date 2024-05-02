# https://www.acmicpc.net/problem/2579
# DP
import sys
n = int(sys.stdin.readline().rstrip())
steps = [0]
for i in range(n):
    steps.append(int(sys.stdin.readline().rstrip()))

dpTable = [0] * (n+1)
dpTable[1] = steps[1]

if n == 1:
    print(dpTable[1])
elif n == 2:
    dpTable[2] = steps[1] + steps[2]
    print(dpTable[2])
else:
    dpTable[2] = steps[1] + steps[2]
    dpTable[3] = max(steps[1]+steps[3], steps[2]+steps[3])
    for i in range(4, n+1):
        dpTable[i] = max(dpTable[i-3]+steps[i-1]+steps[i], dpTable[i-2]+steps[i])

    print(dpTable[-1])