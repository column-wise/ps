import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[] parents;
    static int N;
    public static void main(String[] agrs) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parents = new int[N];
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        int totalCost = 0;
        int count = 0;

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            pq.add(new Node(from, to, cost));
        }

        init();

        while(!pq.isEmpty() && count < N-2) {
            Node node = pq.poll();
            if(union(node.from, node.to)){
                totalCost += node.cost;
                count++;
            }
        }

        System.out.println(totalCost);
    }

    private static class Node implements Comparable<Node>{
        int from;
        int to;
        int cost;

        public Node(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        public int compareTo(Node o) {
            return cost - o.cost;
        }
    }

    private static void init() {
        for(int i = 0; i < N; i++) {
            parents[i] = -1;
        }
    }

    private static int find(int a) {
        if(parents[a] < 0) return a;
        return parents[a] = find(parents[a]);
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return false;
        parents[aRoot] += parents[bRoot];
        parents[bRoot] = aRoot;
        return true;
    }
}