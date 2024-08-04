# https://www.acmicpc.net/problem/14501
# DP
import sys

N = int(sys.stdin.readline().rstrip())
counseling = []
for _ in range(N):
    time, pay = map(int, sys.stdin.readline().rstrip().split())
    counseling.append((time, pay))

dp = [0]*(N+1)

for i in range(N):
    t = counseling[i][0]
    p = counseling[i][1]
    if i+t <= N:       
        dp[i+t] = max(dp[i+t], dp[i]+p)
        for j in range(i+t+1, N+1):
            if dp[j] < dp[i+t]:
                dp[j] = dp[i+t]

print(dp[-1])