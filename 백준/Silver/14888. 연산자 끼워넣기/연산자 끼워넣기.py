# https://www.acmicpc.net/problem/14888
# brute force / permutation
import sys
from itertools import permutations

operand = ['+','-','*','/']

N = int(sys.stdin.readline().rstrip())
nums = list(map(int, sys.stdin.readline().rstrip().split()))
o = list(map(int, sys.stdin.readline().rstrip().split()))

operands = []
for i in range(4):
    for j in range(o[i]):
        operands.append(operand[i])

m = -1000000000
n = 1000000000

for p in set(permutations(operands,len(operands))):
    result = nums[0]
    for i in range(1, N):
        if p[i-1] == '+':
            result += nums[i]
        elif p[i-1] == '-':
            result -= nums[i]
        elif p[i-1] == '*':
            result *= nums[i]
        elif p[i-1] == '/':
            result = int(result / nums[i])
    
    m = max(m, result)
    n = min(n, result)

print(m)
print(n)