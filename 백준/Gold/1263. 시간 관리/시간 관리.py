N = int(input())

works = list()

for i in range(N):
    works.append(tuple(map(int, input().split())))

works.sort(key=lambda x:x[1], reverse=True)

result = 0
deadline = works[0][1]

for i in range(len(works)-1):
    deadline = min(works[i+1][1], deadline - works[i][0])
    result = deadline - works[i+1][0]

if result < 0:
    result = -1

print(result)