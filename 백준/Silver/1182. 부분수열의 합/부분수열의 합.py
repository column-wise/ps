# https://www.acmicpc.net/problem/1182
# brute force
import sys

N, S = map(int, sys.stdin.readline().rstrip().split())
series = list(map(int, sys.stdin.readline().rstrip().split()))
count = 0

for i in range(1, 1<<len(series)):
    sum = 0
    index = bin(i)[2:].zfill(len(series))
    for j in range(len(index)):
        if index[j] == '1':
            sum += series[j]
    if sum == S:
        count += 1

print(count)