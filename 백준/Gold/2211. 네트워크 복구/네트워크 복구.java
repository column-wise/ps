import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] dist;
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Node>[] graph = new ArrayList[N];
        for(int i = 0; i < N; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken())- 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, weight));
            graph[to].add(new Node(from, weight));
        }

        dijkstra(0, graph);

    }

    private static void dijkstra(int start, List<Node>[] graph){
        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[N];
        int[] connect = new int[N];
        dist = new int[N];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(visited[cur.idx]) continue;

            for(Node connected : graph[cur.idx]){
                visited[cur.idx] = true;
                if(dist[connected.idx] > dist[cur.idx] + connected.dist){
                    dist[connected.idx] = dist[cur.idx] + connected.dist;
                    connect[connected.idx] = cur.idx+1;
                    pq.add(new Node(connected.idx, dist[connected.idx]));
                }
            }
        }

        for(int i = 0; i < N; i++){
            if(connect[i] != 0){
                sb.append(i+1).append(" ").append(connect[i]).append("\n");
            }
        }

        System.out.println(N-1);
        System.out.println(sb);
    }

    private static class Node implements Comparable<Node>{
        int idx;
        int dist;

        public Node(int idx, int dist){
            this.idx = idx;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(dist, o.dist);
        }
    }

}