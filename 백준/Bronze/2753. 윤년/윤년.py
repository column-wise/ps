N = int(input())

result = 0

if N % 4 == 0 and N % 100 != 0 or N % 400 == 0:
    result = 1

print(result)