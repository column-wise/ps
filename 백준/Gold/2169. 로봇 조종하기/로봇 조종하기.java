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

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[] prevRowMax = new int[M];
		prevRowMax[0] = map[0][0];
		for(int j = 1; j < M; j++) {
			prevRowMax[j] = prevRowMax[j - 1] + map[0][j];
		}

		for(int i = 1; i < N; i++) {
			int down[] = new int[M];
			for(int j = 0; j < M; j++) {
				down[j] = prevRowMax[j] + map[i][j];
			}

			int left[] = new int[M];
			left[0] = down[0];
			for(int j = 1; j < M; j++) {
				left[j] = Math.max(left[j - 1] + map[i][j], down[j]);
			}

			int right[] = new int[M];
			right[M - 1] = down[M - 1];
			for(int j = M - 2; j >= 0; j--) {
				right[j] = Math.max(right[j + 1] + map[i][j], down[j]);
			}

			int[] cur = new int[M];
			for(int j = 0; j < M; j++) {
				cur[j] = Math.max(left[j], right[j]);
			}

			prevRowMax = cur;
		}

		System.out.println(prevRowMax[M-1]);
	}
}
