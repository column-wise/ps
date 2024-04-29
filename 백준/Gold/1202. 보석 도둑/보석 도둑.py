import sys
import heapq

N, K = map(int, sys.stdin.readline().split())

jewel = []
bag = []
queue = []
answer = 0

for _ in range(N):
    heapq.heappush(jewel, list(map(int, sys.stdin.readline().split())))

for _ in range(K):
    C = int(sys.stdin.readline().rstrip())
    bag.append(C)

bag.sort()

for capacity in bag:
    while jewel and capacity >= jewel[0][0]:
        heapq.heappush(queue, -heapq.heappop(jewel)[1])
    if queue:
        answer -= heapq.heappop(queue)
    elif not jewel:
        break

print(answer)