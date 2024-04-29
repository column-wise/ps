import sys

n = int(sys.stdin.readline())
homeworks = []

for i in range(n):
    d, t = map(int, sys.stdin.readline().split())
    homeworks.append((d,t))

homeworks.sort(key=lambda x:-x[1])
day = homeworks[0][1]

for homework in homeworks:
    if homework[1] < day:
        day = homework[1] - homework[0]

    else:
        day -= homework[0]

print(day)