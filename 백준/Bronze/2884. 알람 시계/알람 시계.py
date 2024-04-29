import sys
H, M = map(int, sys.stdin.readline().rstrip().split())

if M < 45:
    M+=15
    if H == 0:
        H+=23
    else:
        H-=1
else:
    M -= 45

print(H,M)