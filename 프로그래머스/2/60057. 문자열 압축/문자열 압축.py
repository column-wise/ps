def solution(s):
    answer = len(s)
    for i in range(1, (len(s)//2)+1):
        compressed = ''
        last = ''
        count = 1
        for j in range(0, len(s)+1, i):
            if last == s[j:j+i]:
                count += 1
            else:
                if count != 1:
                    compressed += str(count)
                    count = 1
                last = s[j:j+i]
                compressed += s[j:j+i]
        answer = min(answer, len(compressed))
    return answer