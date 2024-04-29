# https://www.acmicpc.net/problem/2675
import sys

T = int(sys.stdin.readline().rstrip())

for _ in range(T):
    R, S = sys.stdin.readline().rstrip().split()
    R = int(R)
    result = []

    for c in S:
        for i in range(R):
            result.append(c)
    
    print(''.join(result))