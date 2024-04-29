import sys

N = int(sys.stdin.readline())

line = list(map(int, sys.stdin.readline().split()))

line.sort()

wait_time = 0
answer = 0

for wait in line:
    wait_time += wait
    answer +=wait_time

print(answer)