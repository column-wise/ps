N, M = map(int, input().split())

price_set = list()
price_each = list()

result = 0

if 1<=N<=100 and 1<=M<=50:
    for i in range(M):
        a, b = map(int, input().split())
        price_set.append(a)
        price_each.append(b)

price_set.sort()
price_each.sort()

while N > 0:

    if N > 6:
        result += min(price_set[0], price_each[0] * 6)

    else:
        result += min(price_set[0], price_each[0] * N)

    N -= 6

print(result)