# https://www.acmicpc.net/problem/10845
# data structure
import sys
N = int(sys.stdin.readline().rstrip())
queue = []

for _ in range(N):
    command = list(map(str, sys.stdin.readline().rstrip().split()))
    if command[0] == "push":
        queue.append(int(command[1]))
    elif command[0] == "pop":
        if queue:
            print(queue[0])
            queue = queue[1:]
        else:
            print(-1)
    elif command[0] == "size":
        print(len(queue))
    elif command[0] == "empty":
        if queue:
            print(0)
        else:
            print(1)
    elif command[0] == "front":
        if queue:
            print(queue[0])
        else:
            print(-1)
    elif command[0] == "back":
        if queue:
            print(queue[-1])
        else:
            print(-1)