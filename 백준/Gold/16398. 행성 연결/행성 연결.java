import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] parents;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        init();
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        long total = 0;

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                pq.add(new Edge(i, j, Integer.parseInt(st.nextToken())));
            }
        }

        while(!pq.isEmpty()) {
            Edge e = pq.poll();
            int from = e.from;
            int to = e.to;
            if(union(from, to)) total += e.cost;
        }

        System.out.println(total);
    }

    public static class Edge implements Comparable<Edge>{
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return cost - o.cost;
        }
    }

    public static void init() {
        parents = new int[N];
        for(int i = 0; i < N; i++) {
            parents[i] = -1;
        }
    }

    public static int find(int a) {
        if(parents[a] < 0) return a;
        return parents[a] = find(parents[a]);
    }

    public static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if(rootA == rootB) return false;

        parents[rootA] += parents[rootB];
        parents[rootB] = rootA;
        return true;
    }
}