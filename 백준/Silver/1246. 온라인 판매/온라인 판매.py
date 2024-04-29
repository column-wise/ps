N, M = map(int, input().split())

price = list()

for i in range(M):
    price.append(int(input()))

price.sort(reverse=True)
result = 0
max_price = 0

for i in range(len(price)):
    x = price[i] * min((i+1), N)
    if result < x:
        result = x
        max_price = price[i]

print(max_price, result)