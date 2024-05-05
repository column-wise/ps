# https://www.acmicpc.net/problem/16173
# DFS
import sys
isPossible = False

def DFS(matrix, x, y):
    global isPossible
    if x >= len(matrix) or y >= len(matrix) or isPossible or matrix[x][y] == 0:
        return
    else:
        distance = max((len(matrix)-1-x), (len(matrix[0])-1-y))
        if distance == matrix[x][y] and (x == len(matrix)-1 or y == len(matrix)-1):
            isPossible = True
            return
        else:
            DFS(matrix, x+matrix[x][y], y)
            DFS(matrix, x, y+matrix[x][y])

N = int(sys.stdin.readline().rstrip())
matrix = []

for i in range(N):
    matrix.append(list(map(int, sys.stdin.readline().rstrip().split())))

DFS(matrix, 0, 0)
if isPossible:
    print("HaruHaru")
else:
    print("Hing")