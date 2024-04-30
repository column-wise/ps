# https://www.acmicpc.net/problem/2979
# implementation
import sys
price = [0]
p = list(map(int, sys.stdin.readline().rstrip().split()))
price = price + p
arrival_departure = []
count = 0

for i in range(3):
    a, b = map(int, sys.stdin.readline().rstrip().split())
    arrival_departure.append((a,'+'))
    arrival_departure.append((b,'-'))

arrival_departure.sort()
current = 0
result = 0

for i in range(6):
    result += (arrival_departure[i][0]-current) * price[count] * count
    current = arrival_departure[i][0]

    if arrival_departure[i][1] == '+':
        count += 1
    else:
        count -= 1
print(result)