import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] coins = new int[N];
        int[][] dp = new int[N+1][K+1];
        dp[0][0] = 1;

        for(int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        for(int i = 1; i <= N; i++) {
            dp[i][0] = 1;
            for(int j = 1; j <= K; j++) {
                for(int k = 0; k * coins[i-1] <= j; k++) {
                    dp[i][j] += dp[i-1][j - k * coins[i-1]];
                }
            }
        }

        System.out.println(dp[N][K]);
    }
}
