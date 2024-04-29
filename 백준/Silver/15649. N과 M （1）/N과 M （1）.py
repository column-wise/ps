# https://www.acmicpc.net/problem/15649
# back tracking
import sys
l = []

def BT(series, M):
    new_series = series
    global l
    if len(new_series) == M:
        print(*new_series)
        return 0
    else:
        for i in l:
            if i in new_series:
                continue
            else:
                new_series.append(i)
                BT(new_series, M)
                new_series.pop()


def main():
    N, M = map(int, sys.stdin.readline().rstrip().split())
    global l
    l = list(i for i in range(1, N+1))
    BT([],M)


if __name__ == '__main__':
    main()