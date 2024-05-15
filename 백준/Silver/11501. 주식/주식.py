# https://www.acmicpc.net/problem/11501

import sys
T = int(sys.stdin.readline().rstrip())

for tc in range(T):
    day = int(sys.stdin.readline().rstrip())
    prices = list(map(int, sys.stdin.readline().rstrip().split()))
    profit = 0
    m = 0

    for i in range(day-1, -1, -1):
        if prices[i] > m:
            m = prices[i]
        else:
            profit += (m-prices[i])

    print(profit)