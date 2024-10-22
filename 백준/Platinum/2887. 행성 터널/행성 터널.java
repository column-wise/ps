import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[] parents;
    static int N;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        Planet[] planets = new Planet[N];
        PriorityQueue<Tunnel> pq = new PriorityQueue<>();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            planets[i] = new Planet(i, x, y, z);
        }

        Arrays.sort(planets, new Comparator<Planet>() {

            @Override
            public int compare(Planet o1, Planet o2) {
                return o1.x - o2.x;
            }
        });

        for(int i = 0; i < N-1; i++){
            pq.add(new Tunnel(planets[i].n, planets[i+1].n, Math.abs(planets[i].x - planets[i+1].x)));
        }

        Arrays.sort(planets, new Comparator<Planet>() {

            @Override
            public int compare(Planet o1, Planet o2) {
                return o1.y - o2.y;
            }
        });

        for(int i = 0; i < N-1; i++){
            pq.add(new Tunnel(planets[i].n, planets[i+1].n, Math.abs(planets[i].y - planets[i+1].y)));
        }

        Arrays.sort(planets, new Comparator<Planet>() {

            @Override
            public int compare(Planet o1, Planet o2) {
                return o1.z - o2.z;
            }
        });

        for(int i = 0; i < N-1; i++){
            pq.add(new Tunnel(planets[i].n, planets[i+1].n, Math.abs(planets[i].z - planets[i+1].z)));
        }
        
        init();
        int sum = 0;

        while(!pq.isEmpty()){
            Tunnel tunnel = pq.poll();
            if(union(tunnel.from, tunnel.to)){
                sum += tunnel.cost;
            }
        }

        System.out.println(sum);
    }

    private static class Planet{
        int n;
        int x;
        int y;
        int z;

        private Planet(int n, int x, int y, int z){
            this.n = n;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    private static class Tunnel implements Comparable<Tunnel>{
        int from;
        int to;
        int cost;

        private Tunnel(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Tunnel o) {
            return Integer.compare(cost, o.cost);
        }
    }

    private static void init(){
        parents = new int[N];
        for(int i = 0; i < N; i++){
            parents[i] = -1;
        }
    }

    private static int find(int a){
        if(parents[a] < 0) return a;
        return parents[a] = find(parents[a]);
    }

    private static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return false;
        parents[aRoot] += parents[bRoot];
        parents[bRoot] = aRoot;
        return true;
    }
}