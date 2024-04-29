N = int(input())

B = list(map(int, input().split()))

A = [0 for _ in range(N)]

count = 0
while True:
    if A == B:
        break
    else:
        count += 1

    odd = -1
    for i in range(N):
        if B[i] % 2 == 1:
            odd = i
            B[i] = B[i] - 1
            break
        
    if odd == -1:
        newB = [i//2 for i in B]
        B = newB


print(count)