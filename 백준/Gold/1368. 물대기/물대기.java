import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[] parents;
    static int n;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        int[] w = new int[n];
        List<Edge> edges = new ArrayList<>();

        int minIdx = -1;
        int min = 100001;
        int minCost = 0;

        for(int i = 0; i < n; i++) {
            w[i] = Integer.parseInt(br.readLine());
            if(w[i] < min) {
                minIdx = i;
                min = w[i];
            }
        }

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                int cost = Integer.parseInt(st.nextToken());
                if(cost != 0) {
                    edges.add(new Edge(i,j,cost));
                }
            }
        }

        for(int i = 0; i < n; i++) {
            edges.add(new Edge(i,n,w[i]));
        }

        makeSet();
        Collections.sort(edges);

        for(Edge e : edges) {
            if (union(e.from, e.to)) {
                minCost += e.cost;
            }
        }
        System.out.println(minCost);
    }

    static class Edge implements Comparable<Edge>{
        int from;
        int to;
        int cost;

        private Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return cost - o.cost;
        }
    }

    static void makeSet() {
        parents = new int[n+1];
        for(int i = 0; i < n+1; i++) {
            parents[i] = -1;
        }
    }

    static int find(int a) {
        if(parents[a] < 0) return a;
        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if(rootA == rootB) return false;

        parents[rootA] += parents[rootB];
        parents[rootB] = rootA;
        return true;
    }
}