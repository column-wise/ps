import sys
result = 0
def Z(N, target, start):
    global result
    if N == 0:
        return
    
    N -= 1
    refer = [start[0] + 2**N, start[1] + 2**N]

    if target[0] < refer[0] and target[1] < refer[1]:
        result += (2**N) * (2**N) * 0
        Z(N, target, start)
    
    elif target[0] < refer[0] and target[1] >= refer[1]:
        result += (2**N) * (2**N) * 1
        start = [start[0], refer[1]]
        Z(N, target, start)
    
    elif target[0] >= refer[0] and target[1] < refer[1]:
        result += (2**N) * (2**N) * 2
        start = [refer[0], start[1]]
        Z(N, target, start)
    
    else:
        result += (2**N) * (2**N) * 3
        start = refer
        Z(N, target, start)


N, r, c = map(int, sys.stdin.readline().rstrip().split())
Z(N, [r,c], [0,0])
print(result)