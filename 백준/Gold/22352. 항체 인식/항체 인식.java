import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int[][] before;
	static int[][] updated;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		before = new int[N][M];
		updated = new int[N][M];
		int[][] after = new int[N][M];
		boolean[][] diff = new boolean[N][M];

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				before[i][j] = Integer.parseInt(st.nextToken());
				updated[i][j] = before[i][j];
			}
		}

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				after[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				diff[i][j] = before[i][j] == after[i][j];
			}
		}

		int si = -1;
		int sj = -1;
		boolean found = false;

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(!diff[i][j]) {
					si = i;
					sj = j;
					found = true;
					break;
				}
			}
			if(found) break;
		}
		
		if(si == -1) {
			System.out.println("YES");
			return;
		}

		bfs(si, sj, after[si][sj]);

		boolean isSame = true;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(updated[i][j] != after[i][j]) {
					isSame = false;
					break;
				}
			}
			if(!isSame) break;
		}

		if(isSame) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}

	private static void bfs(int i, int j, int target) {
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(new Node(i, j));
		boolean[][] visited = new boolean[N][M];
		visited[i][j] = true;
		updated[i][j] = target;

		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			int ci = cur.i;
			int cj = cur.j;

			for(int k = 0; k < 4; k++) {
				int ni = ci + dx[k];
				int nj = cj + dy[k];

				if(ni < 0 || ni >= N || nj < 0 || nj >= M) continue;
				if(visited[ni][nj]) continue;
				if(before[i][j] != before[ni][nj]) continue;
				visited[ni][nj] = true;
				queue.add(new Node(ni, nj));
				updated[ni][nj] = target;
			}
		}
	}

	private static class Node {
		int i;
		int j;
		Node(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
