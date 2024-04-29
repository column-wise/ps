import sys

N = int(sys.stdin.readline().rstrip())

positive = []
negative = []
answer = 0

for i in range(N):
    n = int(sys.stdin.readline().rstrip())

    if n > 1:
        positive.append(n)
    elif n == 1:
        answer += 1
    else:
        negative.append(n)

positive.sort(reverse=True)
negative.sort()

if len(positive) % 2 == 1:
    positive.append(1)

if len(negative) % 2 == 1:
    negative.append(1)

for i in range(0, len(positive), 2):
    answer += positive[i] * positive[i+1]

for i in range(0, len(negative), 2):
    answer += negative[i] * negative[i+1]

print(answer)