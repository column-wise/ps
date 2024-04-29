from heapq import heappush, heappop
from bisect import bisect_left, bisect_right

N, K, T = map(int, input().split())
sharks = list(map(int, input().split()))
canEat = []

sharks.sort()
idx = 0

for i in range(N):
    if sharks[i] < T:
        heappush(canEat, -sharks[i])
        idx = i
    else:
        break

mySize = T

for i in range(K):
    if canEat == []:
        break
    else:
        prey = -heappop(canEat)
        for j in range(bisect_left(sharks, mySize),  bisect_left(sharks, mySize+prey)):
            heappush(canEat, -sharks[j])
        mySize += prey

print(mySize)