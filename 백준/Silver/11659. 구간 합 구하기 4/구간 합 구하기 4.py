import sys
input = sys.stdin.readline

N, M = map(int, input().strip().split())

num_list = list(map(int, input().strip().split()))
S = [0]

for i in range(len(num_list)):
    S.append(S[i]+num_list[i])

for i in range(M):
    s, e = map(int, input().strip().split())
    print(S[e] - S[s-1])