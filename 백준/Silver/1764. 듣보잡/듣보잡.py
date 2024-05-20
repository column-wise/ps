import sys

N, M = map(int, sys.stdin.readline().rstrip().split())
unheard = set()
unseen = set()

for i in range(N):
    s = sys.stdin.readline().rstrip()
    unheard.add(s)

for i in range(M):
    s = sys.stdin.readline().rstrip()
    unseen.add(s)

intersection = sorted(list(unheard & unseen))
print(len(intersection))
for i in range(len(intersection)):
    print(intersection[i])