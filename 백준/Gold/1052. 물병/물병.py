def Binary(n):
    rev_base = ''

    while n > 0:
        n, mod = divmod(n, 2)
        rev_base += str(mod)

    return rev_base


N, K = map(int, input().split())
result = 0

if 1<=N<=10000000 and 1<=K<=1000:
    water = Binary(N)

    while water.count('1') > K:
        buy = 2 ** water.index('1')
        result += buy
        N += buy
        water = Binary(N)

    print(result)