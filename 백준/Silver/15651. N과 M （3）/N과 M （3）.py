import sys
l = []
def BT(series, K):
    new_series = series
    global l
    if len(new_series) == K:
        print(*new_series)
        return
    else:
        for i in range(len(l)):
            new_series.append(l[i])
            BT(new_series,K)
            new_series.pop()

N, K = map(int, sys.stdin.readline().rstrip().split())
l = [i for i in range(1, N+1)]

BT([], K)