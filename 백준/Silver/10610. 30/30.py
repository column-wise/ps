N = int(input())

n = list(map(int, str(N)))

sum = 0
n.sort(reverse=True)

for i in n:
    sum += i

n = "".join(map(str, n))

if sum % 3 == 0 and int(n) % 10 == 0:
    print(n)

else:
    print(-1)