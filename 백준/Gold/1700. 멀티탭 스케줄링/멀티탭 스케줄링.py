import sys

N, K = map(int, sys.stdin.readline().split())

if 1<=N<=100 and 1<=K<=100:

    sequence = list(map(int, sys.stdin.readline().split()))

    powerstrip = []
    answer = 0

    for i in range(K):

        if sequence[i] in powerstrip:
            continue

        if len(powerstrip) < N:
            powerstrip.append(sequence[i])
            continue

        powerstrip_idxs = []

        for j in range(N):
            if powerstrip[j] in sequence[i:]:
                idx = sequence[i:].index(powerstrip[j])
            else:
                idx = 101

            powerstrip_idxs.append(idx)

        del powerstrip[powerstrip_idxs.index(max(powerstrip_idxs))]
        answer += 1
        powerstrip.append(sequence[i])

    print(answer)