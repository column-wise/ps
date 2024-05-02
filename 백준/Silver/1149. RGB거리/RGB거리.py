# https://www.acmicpc.net/problem/1149
# DP
import sys
n = int(sys.stdin.readline().rstrip())
prices = []

for i in range(n):
    prices.append(list(map(int, sys.stdin.readline().rstrip().split())))

dp = [[0]*3 for _ in range(n)]
dp[0][0] = prices[0][0]
dp[0][1] = prices[0][1]
dp[0][2] = prices[0][2]


for i in range(1, n):
    dp[i][0] = min(dp[i-1][1],dp[i-1][2]) + prices[i][0]
    dp[i][1] = min(dp[i-1][0],dp[i-1][2]) + prices[i][1]
    dp[i][2] = min(dp[i-1][0],dp[i-1][1]) + prices[i][2]

print(min(dp[-1]))