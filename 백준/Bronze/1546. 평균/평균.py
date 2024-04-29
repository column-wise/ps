# https://www.acmicpc.net/problem/1546
# greedy
import sys

n = int(sys.stdin.readline().rstrip())
l = list(map(int, sys.stdin.readline().rstrip().split()))
m = max(l)
sum = 0

for i in range(len(l)):
    sum += l[i]/m*100

print(sum/n)