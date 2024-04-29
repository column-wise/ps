N = int(input())

strings = list()
canBeZero = ['A','B','C','D','E','F','G','H','I','J']
match = {'A' : 0, 'B' : 0, 'C' : 0, 'D' : 0, 'E' : 0, 'F' : 0, 'G' : 0, 'H' : 0, 'I' : 0, 'J' : 0}


for _ in range(N):
    expression = input()
    try:
        canBeZero.remove(expression[0])
    except :
        pass
    strings.append(expression)

    for i in range(0, len(expression)):
        match[expression[i]] += 10**(len(expression)-i-1)

match = dict(sorted(match.items(), key=lambda x:x[1]))

for i in match.keys():
    if i in canBeZero:
        match[i] = 0
        break

result = 0
match = list(match.values())
match.sort()

for i in range(10):
    result += int(match[i]) * i

print(result)