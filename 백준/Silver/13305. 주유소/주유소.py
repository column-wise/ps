N = int(input())
road = list(map(int, input().split()))
oil = list(map(int, input().split()))

answer = 0
min_oil_price = oil[0]

for i in range(N-1):
    if min_oil_price > oil[i]:
        min_oil_price = oil[i]

    answer += min_oil_price * road[i]

print(answer)