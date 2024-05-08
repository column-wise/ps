def solution(numbers):
    answer = ''
    temp = list(map(str, numbers))
    temp.sort(key=lambda x:(x * 4)[:4], reverse=True)

    for i in range(len(temp)):
        answer += temp[i]
    
    if answer[0] == '0':
        return '0'
    else:
        return answer