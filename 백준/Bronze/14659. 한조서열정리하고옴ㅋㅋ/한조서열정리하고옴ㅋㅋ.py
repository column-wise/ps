N = int(input())
mountain = list(map(int, input().split()))

result = 0
count = 0
height = -1

for i in range(len(mountain)):
    if mountain[i] < height:
        count += 1
        result = max(result, count)
    else:
        height = mountain[i]
        result = max(result, count)
        count = 0

print(result)