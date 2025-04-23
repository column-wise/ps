import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		int[][] table = new int[N][N];
		int[][] prefixSum = new int[N+1][N+1];

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				table[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for(int i = 1; i <= N; i++) {
			prefixSum[i][1] = prefixSum[i-1][1] + table[i-1][0];
			prefixSum[1][i] = prefixSum[1][i-1] + table[0][i-1];
		}

		for(int i = 2; i <= N; i++) {
			for (int j = 2; j <= N; j++) {
				prefixSum[i][j] = prefixSum[i - 1][j] + prefixSum[i][j - 1] + table[i - 1][j - 1] - prefixSum[i - 1][j - 1];
			}
		}

		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			sb.append(prefixSum[x2][y2] - prefixSum[x1-1][y2] - prefixSum[x2][y1-1] + prefixSum[x1-1][y1-1]).append("\n");
		}

		System.out.print(sb);
	}
}
