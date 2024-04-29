N, K = map(int, input().split())
quest = list(map(int, input().split()))

quest.sort(reverse=True)
exp = 0
k = 0

while quest:
    count = k*quest.pop()
    exp += count
    if k < K:
        k+=1

print(exp)