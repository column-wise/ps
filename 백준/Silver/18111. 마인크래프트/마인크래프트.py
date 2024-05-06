# https://www.acmicpc.net/problem/18111
# brute force
import sys

N, M, B = map(int, sys.stdin.readline().rstrip().split())
ground = []
for i in range(N):
    ground += (list(map(int, sys.stdin.readline().rstrip().split())))

ground.sort(reverse=True)
min_time = 10e8
max_height = 0

for reference in range(257):
    blocks = B
    time = 0
    for g in ground:
        diff = g - reference
        if diff > 0:
            time += diff*2
            blocks += diff         
        else:
            time -= diff
            blocks += diff
            if blocks < 0:
                time = 10e8
                break   
    else:
        min_time = min(min_time, time)
        if min_time == time:
            max_height = max(max_height, reference)

print(min_time, max_height)