# https://www.acmicpc.net/problem/1966
# data structure
import sys

T = int(sys.stdin.readline().rstrip())
for _ in range(T):
    N, M = map(int, sys.stdin.readline().rstrip().split())
    priority = list(map(int, sys.stdin.readline().rstrip().split()))

    l = []
    for i in range(N):
        l.append(tuple((i, priority[i])))
    
    priority.sort(reverse=True)
    count = 1
    while priority:
        if l[0][1] == priority[0]:
            if l[0][0] == M:
                break
            else:
                priority = priority[1:]
                l.pop(0)
                count += 1
        else:
            l.append(l.pop(0))
    
    print(count)