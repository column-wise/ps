# https://www.acmicpc.net/problem/1946
import sys

T = int(sys.stdin.readline().rstrip())

for _ in range(T):
    N = int(sys.stdin.readline().rstrip())
    applicants = []
    selected = []

    for i in range(N):
        a, b = map(int, sys.stdin.readline().rstrip().split())
        applicants.append((a,b))

    applicants.sort(key=lambda x:x[0])
    selected.append(applicants[0])
    best = applicants[0][1]
    for i in range(1, N):
        if applicants[i][1] < best:
            selected.append(applicants)
            best = min(best, applicants[i][1])
    
    print(len(selected))