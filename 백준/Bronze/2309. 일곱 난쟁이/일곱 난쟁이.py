# https://www.acmicpc.net/problem/2309

heights = [int(input()) for _ in range(9)]

diff = sum(heights) - 100
i = 0
while len(heights) == 9:
    for j in range(i+1,9):
        if heights[i] + heights[j] == diff:
            del heights[j]
            del heights[i]
            break
    i += 1

heights.sort()

for h in heights:
    print(h)