from collections import deque
T = 10
for test_case in range(1, T + 1):
    def BFS(graph, start):
        queue = deque()
        queue.append((start,0))
        visited = [False for _ in range(101)]
        visited[start] = True
        visit_sequence = []

        while queue:
            node, visit_order = queue.popleft()
            visit_order += 1
            for n in graph[node]:
                if not visited[n]:
                    visited[n] = True
                    queue.append((n,visit_order))
                    visit_sequence.append((n, visit_order))

        visit_sequence.sort(key=lambda x:(-x[1],-x[0]))
        print(f"#{test_case} {visit_sequence[0][0]}")


    N, start = map(int, input().split())
    graph = [[]for _ in range(101)]
    l = list(map(int, input().split()))

    for i in range(N//2):
        graph[l[2*i]].append(l[(2*i)+1])

    BFS(graph, start)
    # ///////////////////////////////////////////////////////////////////////////////////
