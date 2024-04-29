# https://www.acmicpc.net/problem/9012
N = int(input())

for i in range(N):
    str = input()

    if str[0] == '(' and str[len(str)-1] == ')':
        count = 0
        for j in range(len(str)):
            if str[j] == '(':
                count += 1
            else:
                count -= 1

            if count < 0:
                break
        
        if count == 0:
            print("YES")
        else:
            print("NO")
    
    else:
        print("NO")