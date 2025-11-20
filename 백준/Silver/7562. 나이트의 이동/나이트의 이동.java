import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
	static int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		int[][] board;
		Queue<Point> queue;

		for(int test_case = 1; test_case <= T; test_case++) {
			int I = Integer.parseInt(br.readLine());
			board = new int[I][I];
			for(int i = 0; i < I; i++) {
				Arrays.fill(board[i], 90000);
			}

			int fromX, fromY;
			st = new StringTokenizer(br.readLine());
			fromX = Integer.parseInt(st.nextToken());
			fromY = Integer.parseInt(st.nextToken());

			int toX, toY;
			st = new StringTokenizer(br.readLine());
			toX = Integer.parseInt(st.nextToken());
			toY = Integer.parseInt(st.nextToken());

			queue = new ArrayDeque<>();
			queue.add(new Point(fromX, fromY, 0));
			board[fromX][fromY] = 0;

			while(!queue.isEmpty()) {
				Point cur = queue.poll();
				int curX = cur.x;
				int curY = cur.y;
				int turn = cur.turn;

				for(int d = 0; d < 8; d++) {
					int nx = curX + dx[d];
					int ny = curY + dy[d];

					if(nx < 0 || nx >= I || ny < 0 || ny >= I) continue;
					if(board[nx][ny] <= turn + 1) continue;

					board[nx][ny] = turn + 1;
					queue.add(new Point(nx, ny, turn + 1));
				}
			}

			System.out.println(board[toX][toY]);
		}
	}

	private static class Point {
		int x, y;
		int turn;

		Point(int x, int y, int turn) {
			this.x = x;
			this.y = y;
			this.turn = turn;
		}
	}
}
