# https://www.acmicpc.net/problem/11053
# DP
import sys

n = int(sys.stdin.readline().rstrip())
series = list(map(int, sys.stdin.readline().rstrip().split()))
dp = [1] * n

for i in range(1, n):
    for j in range(i):
        if series[j] < series[i]:
            dp[i] = max(dp[i], dp[j]+1)
print(max(dp))