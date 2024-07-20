# https://www.acmicpc.net/problem/1874
# data structure
import sys

N = int(sys.stdin.readline().rstrip())
series = []
num = 1
stack = []
result = []

for i in range(N):
    series.append(int(sys.stdin.readline().rstrip()))

for i in series:
    if stack and stack[-1] == i:
        stack.pop()
        result.append("-")
        continue

    elif i>=num:
        while i>=num:
            stack.append(num)
            num += 1
            result.append("+")
        stack.pop()
        result.append("-")
    
    else:
        result = []
        break

if result:
    for i in range(len(result)):
        print(result[i])
else:
    print("NO")