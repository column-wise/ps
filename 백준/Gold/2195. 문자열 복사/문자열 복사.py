S = input()
P = input()

count = 0
d = dict()

while P:
    length = min(len(S),len(P))
    for i in range(length):
        token = P[0:length-i]
        if token not in d.keys():
            idx = S.find(token)
            if idx == -1:
                continue
            else:
                d[token] = (idx, length - i)
                count += 1
                P = P[len(token):]
                break
        else:
            count += 1
            P = P[len(token):]
            break

print(count)