# https://www.acmicpc.net/problem/20207
import sys

N = int(sys.stdin.readline().rstrip())
schedules = []

for _ in range(N):
    s, e = map(int, sys.stdin.readline().rstrip().split())
    schedules.append((s,e))

schedules.sort(key=lambda x:(x[0],x[1]))
calendar = [0] * (int(max(schedules, key=lambda x:x[1])[1]) + 1)

for s in schedules:
    for i in range(s[0], s[1]+1):
        calendar[i] += 1

result = 0
while calendar:
    if calendar[0] != 0:
        count = 0
        height = 0
        while calendar and calendar[0] != 0:
            count += 1
            height = max(height, calendar[0])
            calendar = calendar[1:]
        result += count * height
    
    else:
        calendar = calendar[1:]

print(result)