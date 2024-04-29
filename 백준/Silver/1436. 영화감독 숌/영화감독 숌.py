# https://www.acmicpc.net/problem/1436
# brute force
import sys

N = int(sys.stdin.readline().rstrip())
count = 0

for i in range(666,2666800):
    s = str(i)
    for j in range(len(s)-2):
        if s[j:j+3] == '666':
            count += 1
            break
    if count == N:
        print(int(s))
        break