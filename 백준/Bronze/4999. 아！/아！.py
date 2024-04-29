import sys
input = sys.stdin.readline

can = input().strip()
wants = input().strip()

if len(can) >= len(wants):
    print("go")
else:
    print("no")