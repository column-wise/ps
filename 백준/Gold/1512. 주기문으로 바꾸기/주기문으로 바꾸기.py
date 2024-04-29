M = int(input())
string = input()
result = 3000


for i in range(1, M+1):
    count = 0
    for j in range(0, i):
        total = 0
        tok_dict = dict()
        for k in range(j, len(string), i):
            tok = string[k]
            if tok in tok_dict:
                tok_dict[tok] += 1
            else:
                tok_dict[tok] = 1
            total += 1
        count += total - max(tok_dict.values())
    result = min(result, count)
print(result)