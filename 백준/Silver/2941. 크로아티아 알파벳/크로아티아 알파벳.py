# https://www.acmicpc.net/problem/2941
# string / implementation
import sys

CroatiaAlphabet = ["c=","c-","dz=","d-","lj","nj","s=","z="]
CA_first = ["c","d","l","n","s","z"]

s = sys.stdin.readline().rstrip()
count = 0
i = 0
while True:
    if i >= len(s):
        break

    if s[i] in CA_first:
        if i < len(s)-1:
            for j in range(3,1,-1):
                substring = s[i:i+j]
                if substring in CroatiaAlphabet:
                    i += j-1
                    break
        count += 1
        i += 1
    else:
        count += 1
        i += 1

print(count)