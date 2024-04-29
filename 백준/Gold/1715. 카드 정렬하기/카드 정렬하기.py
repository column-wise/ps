from heapq import heappush, heappop

n = int(input())
cards = list()

for _ in range(n):
    heappush(cards,(int(input())))

result = 0
sum = 0

while len(cards) > 1:
    sum = heappop(cards) + heappop(cards)
    result += sum
    heappush(cards, sum)

print(result)