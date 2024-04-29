# https://www.acmicpc.net/problem/11650
# sorting
import sys

N = int(sys.stdin.readline().rstrip())
coord = []

for _ in range(N):
    x, y = map(int, sys.stdin.readline().rstrip().split())
    coord.append((x,y))

coord.sort(key=lambda x:(x[1],x[0]))

for i in range(N):
    print(coord[i][0],coord[i][1])