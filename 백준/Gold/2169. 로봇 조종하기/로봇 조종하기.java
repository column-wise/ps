import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];
		int[][][] dp = new int[N][M][4];

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dp[0][0][3] = map[0][0];
		for(int j = 1; j < M; j++) {
			dp[0][j][3] = dp[0][j - 1][3] + map[0][j];
		}

		for(int i = 1; i < N; i++) {
			for(int j = 0; j < M; j++) {
				dp[i][j][0] = dp[i-1][j][3] + map[i][j];
			}

			dp[i][0][1] = dp[i][0][0];
			for(int j = 1; j < M; j++) {
				dp[i][j][1] = Math.max(dp[i][j-1][1] + map[i][j], dp[i][j][0]);
			}

			dp[i][M-1][2] = dp[i][M-1][0];
			for(int j = M - 2; j >= 0; j--) {
				dp[i][j][2] = Math.max(dp[i][j+1][2] + map[i][j], dp[i][j][0]);
			}

			for(int j = 0; j < M; j++) {
				dp[i][j][3] = Math.max(dp[i][j][1], dp[i][j][2]);
			}
		}

		System.out.println(dp[N-1][M-1][3]);
	}
}
