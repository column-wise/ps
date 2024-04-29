A = int(input())
B = int(input())

n1 = B % 10
n10 = (B // 10) % 10
n100 = B // 100

print(A*n1)
print(A*n10)
print(A*n100)
print(A*n1 + A*n10*10 + A*n100*100)