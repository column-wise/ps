import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		long[] dp = new long[21];
		dp[0] = 1;
		dp[1] = 0;

		for(int i = 2; i <= 20; i++) {
			dp[i] = (i-1) * (dp[i-1] + dp[i-2]);
		}

		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			sb.append(dp[N]).append("\n");
		}
		System.out.println(sb);
	}
}
