import sys
import heapq

n = int(sys.stdin.readline().rstrip())
queue = []
heapq.heapify(queue)

for i in range(n):
    x = int(sys.stdin.readline().rstrip())
    if(x == 0):
        if(len(queue) == 0):
            print(0)
        else:
            print(heapq.heappop(queue))
    elif(x != 0):
        heapq.heappush(queue, x)