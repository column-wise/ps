import sys

N, K = map(int, sys.stdin.readline().split())

A = []

for _ in range(N):
    A.append(int(sys.stdin.readline().rstrip()))

A.sort(reverse=True)
answer = 0
temp = K

for coin in A:
    if coin > temp:
        continue

    else:
        answer += temp//coin
        temp = temp%coin

print(answer)