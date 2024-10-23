import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static final int INF = 10000000;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        List<Node>[] tree = new ArrayList[N];
        for(int i = 0; i < N; i++){
            tree[i] = new ArrayList<>();
        }
        int[] dist = new int[N];
        Arrays.fill(dist, INF);

        for(int i = 0; i < N-1; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            tree[from].add(new Node(to, cost));
            tree[to].add(new Node(from, cost));
        }

        dijkstra(0, tree, dist);

        int max = 0;
        int maxIdx = 0;
        for(int i = 0; i < N; i++){
            if(dist[i] > max){
                max = dist[i];
                maxIdx = i;
            }
        }

        int[] dist2 = new int[N];
        Arrays.fill(dist2, INF);

        dijkstra(maxIdx, tree, dist2);

        max = 0;
        for(int i = 0; i < N; i++){
            if(dist2[i] > max){
                max = dist2[i];
            }
        }

        System.out.println(max);
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

    private static void dijkstra(int start, List<Node>[] tree, int[] dist){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N];

        pq.add(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(visited[cur.idx]) continue;
            visited[cur.idx] = true;

            for(Node next : tree[cur.idx]){
                if(dist[next.idx] > dist[cur.idx] + next.cost){
                    dist[next.idx] = dist[cur.idx] + next.cost;
                    pq.add(new Node(next.idx, dist[next.idx]));
                }
            }
        }
    }
}