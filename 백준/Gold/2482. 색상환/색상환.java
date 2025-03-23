import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        final int MOD = 1000000003;

        int[][] dp = new int[N+1][K+1];
        for(int i = 1; i < N+1; i++) {
            dp[i][0] = 1;
            dp[i][1] = i;
        }

        for(int j = 2; j <= K; j++) {
            for(int i = 4; i <= N; i++) {
                dp[i][j] = (dp[i-2][j-1] + dp[i-1][j]) % MOD;
            }
        }

        System.out.println(dp[N][K]);
    }
}