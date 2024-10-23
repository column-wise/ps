import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static final int INF = 1000000001;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        List<Node>[] graph = new ArrayList[N];
        for(int i = 0; i < N; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, cost));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken()) - 1;
        int end = Integer.parseInt(st.nextToken()) - 1;

        int[] dist = new int[N];
        dijkstra(start, graph, dist);
        System.out.println(dist[end]);
    }

    private static class Node implements Comparable<Node>{
        int idx;
        int cost;

        private Node(int idx, int cost){
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(cost, o.cost);
        }
    }

    private static void dijkstra(int start, List<Node>[] graph, int[] dist){
        Arrays.fill(dist, INF);
        dist[start] = 0;

        boolean[] visited = new boolean[N];
        PriorityQueue<Node> pq = new PriorityQueue();

        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(visited[cur.idx]) continue;
            visited[cur.idx] = true;

            for(Node next : graph[cur.idx]){
                if(dist[next.idx] > dist[cur.idx] + next.cost){
                    dist[next.idx] = dist[cur.idx] + next.cost;
                    pq.offer(new Node(next.idx, dist[next.idx]));
                }
            }
        }
    }
}