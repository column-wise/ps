import sys
n = int(sys.stdin.readline().rstrip())
string = sys.stdin.readline().rstrip()
result = 0
for i in range(len(string)):
    result += int(string[i])
print(result)