# https://www.acmicpc.net/problem/1010

T = int(input())

for i in range(T):
    N, M = map(int, input().split())

    n = 1
    m = 1
    k = 1

    for i in range(1, M+1):
        m *= i

    for i in range(1, M-N+1):
        k *= i
    
    for i in range(1, N+1):
        n *= i
    
    print(m//n//k)