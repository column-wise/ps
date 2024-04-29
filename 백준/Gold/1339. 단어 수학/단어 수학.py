# https://www.acmicpc.net/problem/1339

N = int(input())
words = [input() for i in range(N)]
d = dict()

for word in words:
    for i in range(len(word)):
        if word[i] in d:
            d[word[i]] += pow(10, (len(word)-i-1))
        else:
            d[word[i]] = pow(10, (len(word)-i-1))

d = sorted(d.values(), key=lambda x:-x)

result = 0
i = 9

for c in d:
    result += c*i
    i-=1

print(result)