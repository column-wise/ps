import sys
l = []

def BT(series, K):
    global l
    new_series = series
    if len(new_series) == K:
        print(*new_series)
        return
    else:
        for i in range(len(l)):
            if new_series == [] or new_series[-1] <= l[i]:
                new_series.append(l[i])
                BT(new_series,K)
                new_series.pop()
            else:
                continue

N, K = map(int, sys.stdin.readline().rstrip().split())
l = [i for i in range(1, N+1)]
BT([],K)