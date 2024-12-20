import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static List<Node>[] graph;
    static int[] dist;
    static List<Integer>[] routes;
    static final int INF = 100_000_000;
    public static void main(String [] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        graph = new ArrayList[N+1];

        dist = new int[N+1];
        routes = new ArrayList[N+1];

        for(int i = 0; i <= N; i++) {
            dist[i] = INF;
            graph[i] = new ArrayList<>();
            routes[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, cost));
        }

        st = new StringTokenizer(br.readLine());

        int source = Integer.parseInt(st.nextToken());
        int dest = Integer.parseInt(st.nextToken());

        dijkstra(source);
        routes[dest].add(dest);

        System.out.println(dist[dest]);
        System.out.println(routes[dest].size());
        for(int v : routes[dest]) {
            System.out.print(v + " ");
        }
    }

    private static void dijkstra(int source) {
        dist[source] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(source, 0));

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            int to = node.to;
            int cost = node.cost;

            if(dist[to] < cost) continue;

            for(Node neighbor : graph[to]) {
                if(dist[neighbor.to] > dist[to] + neighbor.cost) {
                    dist[neighbor.to] = dist[to] + neighbor.cost;
                    pq.add(new Node(neighbor.to, dist[neighbor.to]));
                    routes[neighbor.to] = new ArrayList<>(routes[to]);
                    routes[neighbor.to].add(to);
                }
            }
        }
    }

    private static class Node implements Comparable<Node>{
        int to;
        int cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        public int compareTo(Node o) {
            return cost - o.cost;
        }
    }
}