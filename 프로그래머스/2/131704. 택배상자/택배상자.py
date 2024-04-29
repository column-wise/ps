def solution(order):
    answer = 0
    l = 1
    o = 0
    sub = []
    while order and l <= len(order) + 1 and o < len(order):
        if order[o] == l:
            while o < len(order) and order[o] == l:
                o += 1
                answer += 1
                l += 1
            continue
        elif sub and sub[-1] == order[o]:
            while sub and sub[-1] == order[o]:
                o += 1
                answer += 1
                sub.pop()
            continue
        elif order[o] < l or o>= len(order):
            return answer    
        elif order[o] > l:
            sub.append(l)
            l += 1
            continue
        else:
            return answer

    return answer