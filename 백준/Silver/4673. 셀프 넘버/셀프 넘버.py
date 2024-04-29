# https://www.acmicpc.net/problem/4673
# brute force
import time

result = []

for i in range(10000):
    result.append(i)

for i in range(10000):
    d = i
    temp = i//10
    rest = i%10
    while temp > 0:
        d += rest
        rest = temp%10
        temp = temp//10
    d += rest

    if d in result:
        result.remove(d)

for i in result:
    print(i)