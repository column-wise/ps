string = input()
string = string.upper()
words = list(set(string))

l = []

for c in words:
    count = string.count(c)
    l.append(count)

if l.count(max(l)) > 1:
    print("?")
else:
    print(words[l.index(max(l))])