T = int(input())
for test_case in range(1, T + 1):
    N = int(input())
    l = []
    for i in range(N):
        c, n = map(str, input().split())
        n = int(n)
        l.append((c, n))

    result = []
    s = ''
    for i in range(N):
        c, n = l.pop(0)
        for j in range(n):
            s += c
            if len(s) == 10:
                result.append(s)
                s = ''
    result.append(s)

    print(f"#{test_case}")
    for i in range(len(result)):
    	print(result[i])

