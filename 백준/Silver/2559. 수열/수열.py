# https://www.acmicpc.net/problem/2559
# string / implemenatation
import sys
N, days = map(int, sys.stdin.readline().rstrip().split())
temperatures = list(map(int, sys.stdin.readline().rstrip().split()))
result = (-100)*days
sum = 0

for i in range(len(temperatures)):
    sum += temperatures[i]
    if i>=days:
        sum -= temperatures[i-days]
        
    if i>= days-1:
        result = max(result, sum)

print(result)