import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int[] minDist;
    static boolean[] visited;
    static List<Edge>[] edgeList;
    static int V;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        // start vertex
        int K = Integer.parseInt(br.readLine()) - 1;

        // the shortest distance from start vertex
        minDist = new int[V];
        Arrays.fill(minDist, INF);

        visited = new boolean[V];

        edgeList = new ArrayList[V];
        for(int i = 0; i < V; i++){
            edgeList[i] = new ArrayList<>();
        }

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());

            // directed graph
            edgeList[from].add(new Edge(to, weight));
        }

        dijkstra(K);
        for(int i = 0; i < V; i++){
            System.out.println(minDist[i] == INF ? "INF" : minDist[i]);
        }
    }

    public static void dijkstra(int startVertex){
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        minDist[startVertex] = 0;
        pq.offer(new Edge(startVertex, 0));

        while(!pq.isEmpty()){
            Edge cur = pq.poll();
            if(visited[cur.to]) continue;
            visited[cur.to] = true;

            for(Edge next : edgeList[cur.to]){
                if(minDist[cur.to] + next.weight < minDist[next.to]){
                    minDist[next.to] = minDist[cur.to] + next.weight;
                    pq.offer(new Edge(next.to, minDist[next.to]));
                }
            }

        }
    }

    private static class Edge implements Comparable<Edge>{
        int to;
        int weight;

        private Edge(int to, int weight){
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return weight - o.weight;
        }
    }

}