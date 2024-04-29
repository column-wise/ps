N = int(input())
blocks = list(map(int, input().split()))

blocks.sort()
sum = 0
result = 0

for i in range(N):
    sum += blocks[i]

result = max(blocks[N-2], sum/N)

print(result)