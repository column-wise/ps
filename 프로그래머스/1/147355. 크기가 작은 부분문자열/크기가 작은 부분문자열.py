def solution(t, p):
    answer = 0
    for i in range(len(t) - len(p) + 1):
        substring = t[i:i+len(p)]
        if int(substring) <= int(p):
            answer += 1
    return answer