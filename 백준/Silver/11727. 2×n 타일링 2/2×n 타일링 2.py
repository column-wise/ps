import sys

n = int(sys.stdin.readline().rstrip())
dp = [0,1,3]

for i in range(len(dp)-1,n):
    dp.append(dp[i]+2*dp[i-1])

print(dp[n]%10007)