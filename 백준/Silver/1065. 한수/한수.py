# https://www.acmicpc.net/problem/1065
# brute force
import sys

N = int(sys.stdin.readline().rstrip())

if 0 < N < 100:
    print(N)

else:
    result = 99
    for i in range(100, N+1):
        l = []

        share = i//10
        rest = i%10

        while share > 0:
            l.append(rest)
            rest = share % 10
            share = share//10
        
        l.append(rest)
        
        diff = l[1] - l[0]
        check = 0
        for i in range(2, len(l)):
            if l[i] - l[i-1] == diff:
                check += 1
        
        if check == len(l)-2:
            result += 1
    
    print(result)