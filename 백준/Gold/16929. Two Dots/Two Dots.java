import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N, M;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static boolean found = false;
	static char[][] board;
	static boolean[][] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = br.readLine().split(" ");
		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);

		board = new char[N][M];
		visited = new boolean[N][M];
		for(int i = 0; i < N; i++) {
			inputs = br.readLine().split("");
			for(int j = 0; j < M; j++) {
				board[i][j] = inputs[j].charAt(0);
			}
		}

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {

				if(!found) {
					visited[i][j] = true;
					dfs(-1, -1, i, j, board[i][j]);
					visited[i][j] = false;
				}
			}
		}

		System.out.println(found? "Yes" : "No");
	}

	private static boolean dfs(int px, int py, int cx, int cy, char color) {
		for(int d = 0; d < 4; d++) {
			int nx = cx + dx[d];
			int ny = cy + dy[d];

			if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
			if(nx == px && ny == py) continue;
			if(board[nx][ny] != color) continue;

			if(visited[nx][ny]) return true;

			visited[nx][ny] = true;
			found |= dfs(cx, cy, nx, ny, color);
			visited[nx][ny] = false;
		}

		return found;
	}
}
