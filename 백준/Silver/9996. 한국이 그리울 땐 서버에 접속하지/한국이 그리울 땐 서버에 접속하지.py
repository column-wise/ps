# https://www.acmicpc.net/problem/9996
# string / implementation
import sys

N = int(sys.stdin.readline().rstrip())
pattern = sys.stdin.readline().rstrip()
star_index = pattern.index('*')
head = pattern[:star_index]
tail = pattern[star_index+1:]

for _ in range(N):
    s = sys.stdin.readline().rstrip()
    correct = False

    for i in range(len(head)):
        if len(s) >= len(head) and head[i] == s[i]:
            continue
        else:
            break
    else:
        s=s[i+1:]
        for j in range(len(tail)):
            if len(s) >= len(tail) and tail[-1-j] == s[-1-j]:
                continue
            else:
                break
        else:
            correct = True
    
    if correct:
        print("DA")
    else:
        print("NE")