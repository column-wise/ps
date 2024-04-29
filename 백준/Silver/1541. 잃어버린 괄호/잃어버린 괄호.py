# https://www.acmicpc.net/problem/1541

exp = input()
temp = []
result = []

while exp:
    if exp[0].isdigit():
        temp.append(exp[0])
        exp = exp[1:]
    elif exp[0] == '+':
        if temp:
            result.append(str(int(''.join(temp))))
            temp = []
        result.append(exp[0])
        exp = exp[1:]
    else:
        if temp:
            result.append(str(int(''.join(temp))))
            temp = []
        result.append(exp[0])
        exp = exp[1:]
        result.append('(')
        
        while exp and exp[0] != '-':
            if exp[0].isdigit():
                temp.append(exp[0])
                exp = exp[1:]
            else:
                if temp:
                    result.append(str(int(''.join(temp))))
                    temp = []
                result.append(exp[0])
                exp = exp[1:]
        result.append(str(int(''.join(temp))))
        temp =  []
        result.append(')')
if temp:
    result.append(str(int(''.join(temp))))

print(eval(''.join(result)))