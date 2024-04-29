N, L = map(int, input().split())
leaks = list(map(int, input().split()))

leaks.sort()

intervals = list()
i = 0


while i < len(leaks):
    j = len(leaks) - 1
    while j >= i:
        interval = leaks[j] - leaks[i]

        if interval+1 <= L:
            intervals.append(interval)
            i = j+1
            break
        else:
            j-=1
            continue

print(len(intervals))