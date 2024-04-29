def solution(s):
    open = '([{'
    close = ')]}'
    answer = 0

    for i in range(len(s)):
        o = []
        bracket = ['()','{}','[]']
        s = str(s[1:])+str(s[0])
        for j in range(len(s)):
            if s[j] in close and o and (o.pop() + s[j]) in bracket:
                continue
            elif s[j] in open:
                o.append(s[j])
            else:
                break
        else:
            if not o:
                answer += 1

    return answer