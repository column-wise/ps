from heapq import heappush, heappop

N = int(input())

count = 0
classes = list()
rooms = []

for i in range(N):
    C, S, E = map(int, input().split())
    classes.append((S,E))

classes.sort()

for cls in classes:
    heappush(rooms, cls[1])
    while rooms and cls[0] >= rooms[0]:
        heappop(rooms)

    count = max(count, len(rooms))

print(count)