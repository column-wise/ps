# https://www.acmicpc.net/problem/9625
# DP
import sys
k = int(sys.stdin.readline().rstrip())
dp = [(1,0),(0,1)]

for i in range(2, k+1):
    dp.append(tuple((dp[i-2][0] + dp[i-1][0], dp[i-2][1] + dp[i-1][1])))

print(dp[-1][0], dp[-1][1])