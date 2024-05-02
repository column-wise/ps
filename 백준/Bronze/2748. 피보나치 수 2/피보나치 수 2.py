# https://www.acmicpc.net/problem/2748
# DP
import sys
dp = [0,1]
n = int(sys.stdin.readline().rstrip())

for i in range(2, n+1):
    dp.append(dp[i-2]+dp[i-1])

print(dp[n])