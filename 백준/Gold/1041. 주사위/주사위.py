import sys
N = int(sys.stdin.readline().rstrip("\n"))
list = list(map(int, sys.stdin.readline().rstrip("\n").split()))

min1 = min(list[0],list[5])
min2 = min(list[1],list[4])
min3 = min(list[2],list[3])

min = [min1,min2,min3]
min.sort()

xxs = min[0]
xs = min[1]
s = min[2]

if N == 1:
    list.sort()
    print(list[0]+list[1]+list[2]+list[3]+list[4])

else:
    print((xxs+xs+s)*4 + (xxs+xs)*4 + (N-2)*(xxs+xs)*8 + (N-2)*(N-2)*xxs + (N-2)*(N-1)*4*xxs)