# https://www.acmicpc.net/problem/2798
# brute force
import sys

N, M = map(int, sys.stdin.readline().rstrip().split())
cards = list(map(int, sys.stdin.readline().rstrip().split()))

result = 0
for i in range(len(cards)-2):
    for j in range(i+1, len(cards)-1):
        for k in range(j+1, len(cards)):
            if cards[i]+cards[j]+cards[k] <= M:
                result = max(result, cards[i]+cards[j]+cards[k])

print(result)