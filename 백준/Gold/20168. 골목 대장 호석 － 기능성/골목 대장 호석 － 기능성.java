import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int A;
    static int B;
    static int C;
    static int minShame;
    static int minMoney;
    static List<List<Edge>> graph;
    static boolean[] visited;
    static final int INF = 1000_000_000;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N: 노드 개수, M: 엣지 개수, A: 시작지, B: 도착지, C: 가진 돈
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken()) - 1;
        B = Integer.parseInt(st.nextToken()) - 1;
        C = Integer.parseInt(st.nextToken());

        minShame = INF;
        visited = new boolean[N];
        graph = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Edge(to, weight));
            graph.get(to).add(new Edge(from, weight));
        }

        dfs(A, 0, 0);

        System.out.println(minShame != INF ? minShame : -1);
    }

    private static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    private static void dfs(int cur, int max, int sum) {
        if(sum > C) return;
        if(cur == B) {
            if(minShame >= max) {
                minShame = max;
                minMoney = sum;
            }
            return;
        }

        for(Edge e : graph.get(cur)) {
            if(visited[e.to]) continue;
            visited[e.to] = true;
            dfs(e.to, Math.max(max, e.weight), sum + e.weight);
            visited[e.to] = false;
        }
    }
}