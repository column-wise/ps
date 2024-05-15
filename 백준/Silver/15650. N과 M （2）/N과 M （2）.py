# https://www.acmicpc.net/problem/15650
# back tracking
import sys
l = []

def BT(series, M, index):
    new_series = series
    if len(new_series) == M:
        print(*new_series)
        return
    else:
        global l
        for i in range(index, len(l)):
            if l[i] in new_series:
                continue
            else:
                new_series.append(l[i])
                BT(new_series, M, i)
                new_series.pop()


N, M = map(int, sys.stdin.readline().rstrip().split())
l = [i for i in range(1, N+1)]
BT([],M, 0)