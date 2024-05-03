T = int(input())

dx = [0,1,0,-1]
dy = [1,0,-1,0]

def DFS(matrix, direction, x, y, num):
    if x > len(matrix)-1 or y > len(matrix[0])-1 or matrix[x][y] != 0:
        return
    else:
        matrix[x][y] += num
        if 0 <= x+dx[direction] < len(matrix) and 0 <= y+dy[direction] < len(matrix[0]) and matrix[x+dx[direction]][y+dy[direction]] == 0:
            pass
        else:
            direction += 1
            direction %= 4
        
        DFS(matrix, direction, x+dx[direction], y+dy[direction], num+1)

# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    # ///////////////////////////////////////////////////////////////////////////////////
    N = int(input())
    matrix = [[0 for _ in range(N)] for _ in range(N)]
    DFS(matrix, 0, 0, 0, 1)
    
    print(f"#{test_case}")

    for i in range(N):
        for j in range(N):
            print(matrix[i][j], end=' ')
            if j==(N-1):
                print()