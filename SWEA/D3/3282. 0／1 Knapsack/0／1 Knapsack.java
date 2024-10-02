import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] V = new int[N];
            int[] C = new int[N];
            int[] dp = new int[K+1];
            int max = 0;

            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                V[i] = Integer.parseInt(st.nextToken());
                C[i] = Integer.parseInt(st.nextToken());
            }

            for(int i = 0; i < N; i++){
                for(int j = K; j > 0; j--){
                    int v = V[i];
                    int c = C[i];

                    if(j-v >= 0){
                        dp[j] = Math.max(dp[j], dp[j - v] + c);
                    }
                }
            }

            for(int i = 0; i < K+1; i++) {
                if(max < dp[i]) {
                    max = dp[i];
                }
            }

            System.out.println("#"+test_case+ " " + max);
        }
    }
}