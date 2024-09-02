import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static int[] parents;
	static int M;
	static int N;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		map = new int[M][N];
		parents = new int[M * N];

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			for (int j = y1; j < y2; j++) {
				for (int k = x1; k < x2; k++) {
					map[j][k] = 1;
				}
			}

		}

		for (int j = 0; j < M * N; j++) {
			if (map[j / N][j % N] == 1)
				continue;
			parents[j] = -1;
		}

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				int x1 = i;
				int y1 = j;

				for (int k = 0; k < 4; k++) {
					int x2 = x1 + dx[k];
					int y2 = y1 + dy[k];

					if (x2 < 0 || x2 >= M || y2 < 0 || y2 >= N) {
						continue;
					}
					// System.out.println("x1="+x1+" y1="+y1+" x2="+x2+" y2="+y2);
					union(x1 * N + y1, x2 * N + y2);

				}
			}
		}
		
		List<Integer> result = new ArrayList<>();

		for (int i = 0; i < M * N; i++) {
			if(parents[i] < 0) {
				result.add(-parents[i]);
			}
		}
		
		System.out.println(result.size());
		Collections.sort(result);
		for(int i = 0; i < result.size(); i++) {
			System.out.print(result.get(i)+" ");
		}

	}

	static int find(int n) {
		if (map[n / N][n % N] == 1)
			return -1;
		if (parents[n] < 0)
			return n;
		return parents[n] = find(parents[n]);
	}

	static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA == -1 || rootB == -1)
			return false;
		if (rootA == rootB)
			return false;
		parents[rootA] += parents[rootB];
		parents[rootB] = rootA;
		return true;
	}
}