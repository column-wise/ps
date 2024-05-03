# https://www.acmicpc.net/problem/7490
# implementation
def recur(s, result, operator, index):
    if index == len(s):
            return
    else:
        result += operator 
        result += str(s[index])
        if eval(result.replace(' ','')) == 0 and index == len(s)-1:
            print(result)
            return
        else:
            recur(s, result, ' ', index+1)
            recur(s, result, '+', index+1)
            recur(s, result, '-', index+1)



import sys
T = int(sys.stdin.readline().rstrip())
for _ in range(T):
    N = int(sys.stdin.readline().rstrip())
    series = [i+1 for i in range(N)]
    result = str(series[0])
    recur(series, result, ' ',1)
    recur(series, result, '+',1)
    recur(series, result, '-',1)
    print()