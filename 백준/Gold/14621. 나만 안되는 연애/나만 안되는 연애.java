import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] parents;
    static int V;
    static int E;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E  = Integer.parseInt(st.nextToken());
        int[] gender  = new int[V];
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < V; i++){
            String g = st.nextToken();
            if(g.equals("M")){
                gender[i] = 0;
            } else {
                gender[i] = 1;
            }
        }

        makeSet();
        PriorityQueue<Edge> queue = new PriorityQueue<>();

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int cost  = Integer.parseInt(st.nextToken());

            queue.add(new Edge(a,b,cost));
        }

        int connectCnt = 1;
        int minCost = 0;
        while(!queue.isEmpty()){
            Edge e = queue.poll();
            if((gender[e.from] != gender[e.to]) && (find(e.from) != find(e.to))){
                union(e.from, e.to);
                minCost += e.cost;
                connectCnt++;
            }
        }

        if(connectCnt == V){
            System.out.println(minCost);
        }else{
            System.out.println(-1);
        }

    }

    static class Edge implements Comparable<Edge>{
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return cost - o.cost;
        }
    }

    static private void makeSet(){
        parents = new int[V];
        for(int i = 0; i < V; i++){
            parents[i] = -1;
        }
    }

    static int find(int a){
        if(parents[a] < 0) return a;
        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);

        if(rootA == rootB) return false;

        parents[rootA] += parents[rootB];
        parents[rootB] = rootA;
        return true;
    }

}