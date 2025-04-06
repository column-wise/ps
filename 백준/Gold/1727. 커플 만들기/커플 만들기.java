import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int couples = Math.min(N, M);
		int[] boys = new int[N+1];
		int[] girls = new int[M+1];

		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			boys[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= M; i++) {
			girls[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(boys);
		Arrays.sort(girls);

		int[][] dp = new int[N+1][M+1];

		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				if(i == j) {
					dp[i][j] = dp[i-1][j-1] + Math.abs(boys[i] - girls[j]);
				} else if(i > j) {
					dp[i][j] = Math.min(dp[i-1][j-1] + Math.abs(boys[i] - girls[j]), dp[i-1][j]);
				} else {
					dp[i][j] = Math.min(dp[i-1][j-1] + Math.abs(boys[i] - girls[j]), dp[i][j-1]);
				}
			}
		}

		System.out.println(dp[N][M]);
	}
}
