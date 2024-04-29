# https://www.acmicpc.net/problem/2108
# math, sorting
import sys

N = int(sys.stdin.readline().rstrip())
l = []

sum = 0
d = dict()

for _ in range(N):
    n = int(sys.stdin.readline().rstrip())
    sum += n
    l.append(n)

    if n in d.keys():
        d[n] = d[n] + 1
    else:
        d[n] = 1

l.sort()
d = list(d.items())
d.sort(key=lambda x : (x[1],-x[0]), reverse=True)

often = 0
if len(d) > 1 and d[0][1] == d[1][1]:
    often = d[1][0]
else:
    often = d[0][0]

print(int(round(sum/N, 0)))
print(l[(N-1)//2])
print(often)
print(l[-1] - l[0])