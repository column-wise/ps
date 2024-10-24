import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] parents;
    static int[] candies;
    static int N;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        candies = new int[N];
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            candies[i] = Integer.parseInt(st.nextToken());
        }
        
        init();

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            union(from, to);
        }

        List<Integer> weight = new ArrayList<>();
        List<Integer> price = new ArrayList<>();

        for(int i = 0; i < N; i++){
            if(parents[i] < 0){
                weight.add(-parents[i]);
                price.add(candies[i]);
            }
        }

        int size = weight.size();
        int[][] dp = new int[size+1][K];

        for(int i = 1; i <= size; i++){
        	for(int j = 0; j < K; j++){
                if (j >= weight.get(i-1)) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weight.get(i-1)] + price.get(i-1));
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        System.out.println(dp[size][K-1]);

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
        candies[aRoot] += candies[bRoot];
        candies[bRoot] = 0;
        parents[bRoot] = aRoot;
        return true;
    }
}