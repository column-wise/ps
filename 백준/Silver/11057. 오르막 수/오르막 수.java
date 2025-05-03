import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[][] dp = new int[1002][10];
		int sum = 0;
		for(int i = 0; i < 10; i++) {
			dp[1][i] = 10-i;
			sum += dp[1][i];
		}
		dp[2][0] = sum;

		for(int i = 2; i <= N; i++) {
			sum = dp[i][0];
			for(int j = 1; j < 10; j++) {
				dp[i][j] = (dp[i][j-1] - dp[i-1][j-1] < 0 ? dp[i][j-1] + 10007 - dp[i-1][j-1] : dp[i][j-1] - dp[i-1][j-1]) % 10007;
				sum = (sum + dp[i][j]) % 10007;
			}
			dp[i+1][0] = sum;
		}

		System.out.println(dp[N][0]);
	}
}
