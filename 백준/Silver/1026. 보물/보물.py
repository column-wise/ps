import sys

N = int(sys.stdin.readline().rstrip())

A = list(map(int, sys.stdin.readline().split()))
B = list(map(int, sys.stdin.readline().split()))

A.sort()
B.sort(reverse=True)

answer = 0

for i in range(N):
    answer += A[i] * B[i]

print(answer)