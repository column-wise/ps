import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken()) - 1;

        List<Integer>[] adjList = new ArrayList[N];
        List<Integer>[] revAdjList = new ArrayList[N];

        for(int i = 0; i < N; i++){
            adjList[i] = new ArrayList<>();
            revAdjList[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            adjList[a].add(b);
            revAdjList[b].add(a);
        }

        int lower = bfs(adjList, X);
        int upper = bfs(revAdjList, X);

        sb.append(upper+1).append(" ").append(N-lower).append("\n");
        System.out.println(sb);
    }

    private static int bfs(List<Integer>[] graph, int X){
        Deque<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N];
        int count = 0;

        queue.add(X);
        visited[X] = true;

        while(!queue.isEmpty()){
            int cur = queue.poll();
            for(int next : graph[cur]){
                if(visited[next]) continue;

                visited[next] = true;
                count++;
                queue.add(next);
            }
        }

        return count;
    }

}