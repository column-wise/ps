import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int V;
    static final int INF = 1_600_000_000;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        List<Edge>[] edgeList = new ArrayList[V+1];
        List<Edge>[] reverseEdgeList = new ArrayList[V+1];
        int[][] dist = new int[V+1][V+1];
        int min = INF;

        for(int i = 1; i <= V; i++) {
            edgeList[i] = new ArrayList<>();
            reverseEdgeList[i] = new ArrayList<>();
            for(int j = 1; j <= V; j++){
                if(i==j) continue;
                dist[i][j] = INF;
            }
        }

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edgeList[from].add(new Edge(to, weight));
            reverseEdgeList[to].add(new Edge(from, weight));
        }

        for(int i = 1; i <= V; i++) {
            dijkstra(edgeList, dist, i);

            for(Edge in : reverseEdgeList[i]) {
                if(dist[i][in.to] != INF){
                    min = Math.min(min, dist[i][in.to] + in.weight);
                }
            }
        }

        System.out.println(min != INF ? min : -1);
    }
    private static class Edge implements Comparable<Edge>{
        int to;
        int weight;

        private Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(weight, o.weight);
        }
    }

    private static void dijkstra(List<Edge>[] edgeList, int[][] dist, int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[V+1];

        pq.add(new Edge(start, 0));

        while(!pq.isEmpty()) {
            Edge edge = pq.poll();
            int cur = edge.to;

            if (visited[cur]) continue;
            visited[cur] = true;

            for(Edge e : edgeList[cur]) {
                if (dist[start][e.to] > dist[start][cur] + e.weight) {
                    dist[start][e.to] = dist[start][cur] + e.weight;
                    pq.add(new Edge(e.to, e.weight));
                }
            }
        }
    }
}