# https://www.acmicpc.net/problem/14916
# greedy
import sys

n = int(sys.stdin.readline().rstrip())

if n != 1 and n != 3:
    share = n//5
    rest = n%5

    if rest%2 == 1:
        share -= 1
        rest += 5

    result = share + rest//2
    print(result)
else:
    print(-1)