import sys

N = int(input())
triangle_l_list = list()

for _ in range(N):
    triangle_l_list.append(int(sys.stdin.readline()))

triangle_l_list.sort(reverse=True)
result = []

for i in range(N-2):
    triangle = triangle_l_list[i:i+3]
    l0 = triangle[0]
    l1 = triangle[1]
    l2 = triangle[2]

    if l1 + l2 <= l0:
        result.append(-1)
    else:
        result.append(l0 + l1 + l2)

print(max(result))