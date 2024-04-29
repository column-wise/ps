N = int(input())
K = int(input())

sensors = list(map(int, input().split()))
sensors.sort()
distances = []

for i in range(len(sensors)-1):
    distances.append(sensors[i+1] - sensors[i])

distances.sort()
sum = 0

for i in range(N-K):
    sum += distances[i]

print(sum)