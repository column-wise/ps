import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    static int[] parents;
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int total = 0;
        int linked = 0;
        int count = 0;

        int[][] network = new int[N][N];
        for(int i = 0; i < N; i++) {
            String input = br.readLine();
            for(int j = 0; j < N; j++) {
                network[i][j] = getLength(input.charAt(j));
                total += network[i][j];
            }
        }

        init();
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(i == j) continue;
                if(network[i][j] == 0) continue;
                pq.add(new Edge(i, j, network[i][j]));
            }
        }

        while(!pq.isEmpty()) {
            Edge edge = pq.poll();
            int from = edge.from;
            int to = edge.to;
            int weight = edge.weight;

            if(union(from, to)) {
                linked += weight;
            }
        }

        for(int i = 0; i < N; i++) {
            count = Math.min(count, parents[i]);
        }
        if(N + count == 0) {
            System.out.println(total - linked);
        } else {
            System.out.println(-1);
        }
    }

    private static int getLength(char c) {
        if(Character.isUpperCase(c)) {
            return c - 'A' + 27;
        } else if(Character.isLowerCase(c)) {
            return c - 'a' + 1;
        } else {
            return 0;
        }
    }

    private static void init() {
        parents = new int[N];
        for(int i = 0; i < N; i++) {
            parents[i] = -1;
        }
    }

    private static int find(int a) {
        if(parents[a] < 0) return a;
        parents[a] = find(parents[a]);
        return parents[a];
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return false;
        parents[aRoot] += parents[bRoot];
        parents[bRoot] = aRoot;
        return true;
    }

    private static class Edge implements Comparable<Edge>{
        int from;
        int to;
        int weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public int compareTo(Edge o) {
            return weight - o.weight;
        }
    }
}