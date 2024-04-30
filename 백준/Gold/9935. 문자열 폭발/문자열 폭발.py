# https://www.acmicpc.net/problem/9935
# string / implementation / data structure
import sys
from collections import deque

s = sys.stdin.readline().rstrip()
bomb = sys.stdin.readline().rstrip()
result = []
stack = deque()

for i in range(len(s)):
    stack.append(s[i])
    if len(stack) > len(bomb):
        result.append(stack.popleft())

    if ''.join(stack) == bomb:
        stack = deque()
        for j in range(len(bomb)):
            try:
                stack.appendleft(result.pop())
            except:
                pass

result = result + list(stack)

if result:
    print(''.join(result))
else:
    print("FRULA")