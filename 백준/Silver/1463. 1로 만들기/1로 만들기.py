n = int(input())
if 1 <= n <= 1000000:

    D = [0] * (n+1)
    D[0] = 0
    D[1] = 0

    for i in range(2, n+1):
        if i % 3 == 0 and i % 2 == 0:
            D[i] = min(D[int(i/3)], D[int(i/2)], D[i-1]) + 1

        elif i % 3 == 0:
            D[i] = min(D[int(i/3)], D[i-1]) + 1

        elif i % 2 == 0:
            D[i] = min(D[int(i/2)], D[i-1]) + 1

        else:
            D[i] = D[i-1] + 1

    print(D[n])