N = int(input())

if 1<=N<=100000:
    A = list(map(int, input().split()))
    B = list(map(int, input().split()))
    v = []

    extention = 0

    for i in range(N):
        v.append([A[i], B[i]])

    v.sort(key=lambda x:x[0])
    v.sort(key=lambda x:x[1])

    prev_max = v[0][1]
    cur_max = -1

    for i in range(N):
        if v[i][0] < prev_max:

            if prev_max < v[i][1]:
                prev_max = v[i][1]

            count = (prev_max - v[i][0] + 29) // 30
            v[i][0] += count*30
            extention += count

        cur_max = max(cur_max, v[i][0])

        if (i+1) < N and v[i][1] != v[i+1][1]:
            prev_max = cur_max

    print(extention)