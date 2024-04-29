def combination(n, r):
    c = 1
    p = 1

    while(r > 0):
        c *= n
        n -= 1
        p *= r
        r -= 1

    return c/p

N, M, K = map(int, input().split())

if 2 <= N <= 8 and 1 <= M <= N-1 and 1 <= K <= M:

    result = 0

    while K <= M:
        if N - M < M - K:
            K += 1
            continue

        result += combination(M, K) * combination(N-M, M-K) / combination(N, M)
        K += 1

    print(result)