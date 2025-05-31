import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());

			int[] sums = new int[N+1];
			int[][] dp = new int[N+1][N+1];
			for(int i = 1; i <= N; i++) {
				sums[i] = sums[i-1] + Integer.parseInt(st.nextToken());
			}

			for(int k = 1; k < N; k++) {
				for(int from = 1; from + k <= N; from++) {
					int to = from + k;
					dp[from][to] = Integer.MAX_VALUE;
					for(int middle = from; middle < to; middle++) {
						dp[from][to] = Math.min(dp[from][to], dp[from][middle] + dp[middle+1][to] + sums[to] - sums[from-1]);
					}
				}
			}

			sb.append(dp[1][N]).append("\n");
		}

		System.out.println(sb);
	}
}
