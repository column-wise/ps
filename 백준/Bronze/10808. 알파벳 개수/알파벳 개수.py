# https://www.acmicpc.net/problem/10808
import sys
s = sys.stdin.readline().rstrip()
result = [0] * 26

for c in s:
    result[ord(c)-97] += 1

print(*result)