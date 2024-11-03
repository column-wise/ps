import sys
input = sys.stdin.readline
def solution(diffs, times, limit):
    diffs.insert(0,0)
    times.insert(0,0)
    puzzleCnt = len(diffs)
    levelEnd = max(diffs)
    start = 1
    end = levelEnd
    level = -1
    result = -1
    answer = []

    while start <= end :
        level = (start + end) // 2
        time = 0
        for j in range(1,puzzleCnt) :
            if diffs[j] <= level :
                time += times[j]
            else :
                time += (((diffs[j] - level) * (times[j] + times[j-1])) + times[j])

        if time <= limit :
            end = level - 1
            result = level
        else :
            start = level + 1

    return result