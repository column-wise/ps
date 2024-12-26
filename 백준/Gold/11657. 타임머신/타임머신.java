import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static List<Edge> edges;
    static long[] dist;
    static final long INF = Long.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = new ArrayList<>();
        dist = new long[N];

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            edges.add(new Edge(from, to, cost));
        }

        if(bellmanFord(0)) {
            for(int i = 1; i < N; i++) {
                System.out.println(dist[i] != INF ? dist[i] : -1);
            }
        } else {
            System.out.println(-1);
        }
    }

    private static boolean bellmanFord(int s) {

        boolean isValid = true;
        for(int i = 0; i < N; i++) {
            dist[i] = INF;
        }
        dist[s] = 0;

        for(int i = 1; i <= N; i++) {
            for(int j = 0; j < M; j++) {
                Edge e = edges.get(j);

                int curNode = e.from;
                int nextNode = e.to;
                int cost = e.cost;

                if(dist[curNode] != INF && dist[nextNode] > dist[curNode] + cost) {
                    dist[nextNode] = dist[curNode] + cost;

                    if(i == N) {
                        isValid = false;
                    }
                }
            }
        }

        return isValid;
    }

    private static class Edge {
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
}