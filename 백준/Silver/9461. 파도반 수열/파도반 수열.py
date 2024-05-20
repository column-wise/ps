import sys

T = int(sys.stdin.readline().rstrip())
dp = [0,1,1,1,2]
for test_case in range(T):
    n = int(sys.stdin.readline().rstrip())
    if n >= len(dp):
        for i in range(len(dp)-1,n):
            dp.append(dp[i]+dp[i-4])
    
    print(dp[n])