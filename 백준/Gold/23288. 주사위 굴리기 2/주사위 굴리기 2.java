import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] parents;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] board = new int[N][M];
		int[][] scores = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		init();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				for(int k = 0; k < 4; k++) {
					int ni = i + dx[k];
					int nj = j + dy[k];
					if(ni < 0 || ni >= N || nj < 0 || nj >= M) continue;
					if(board[ni][nj] == board[i][j]) {
						union(i*M+j, ni*M+nj);
					}
				}
			}
		}

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				scores[i][j] = board[i][j] * (parents[i*M+j] < 0 ? -parents[i*M+j] : -parents[find(i*M+j)]);
			}
		}

		int score = 0;
		Dice dice = new Dice(0, 0);
		int direction = 0;

		for(int i = 0; i < K; i++) {
			int curX = dice.x;
			int curY = dice.y;
			
			int nx = curX + dx[direction];
			int ny = curY + dy[direction];
			if(nx < 0 || nx >= N || ny < 0 || ny >= M) {
				direction = (direction+2) % 4;
				nx = curX + dx[direction];
				ny = curY + dy[direction];
			}

			dice.move(direction);
			score += scores[dice.x][dice.y];

			if(dice.cells[5] > board[dice.x][dice.y]) {
				direction = (direction+1) % 4;
			} else if(dice.cells[5] < board[dice.x][dice.y]) {
				direction = (direction+3) % 4;
			}
		}

		System.out.println(score);
	}

	private static class Dice {
		int x, y;
		int[] cells;

		Dice(int x, int y) {
			this.x = x;
			this.y = y;
			cells = new int[6];
			cells[0] = 1;
			cells[1] = 2;
			cells[2] = 3;
			cells[3] = 4;
			cells[4] = 5;
			cells[5] = 6;
		}

		void move(int direction) {
			if(direction == 0) {
				moveEast();
			} else if(direction == 1) {
				moveSouth();
			} else if(direction == 2) {
				moveWest();
			} else if(direction == 3) {
				moveNorth();
			}
		}

		void moveEast() {
			y += 1;

			int temp = cells[2];
			cells[2] = cells[0];
			cells[0] = cells[3];
			cells[3] = cells[5];
			cells[5] = temp;
		}

		void moveWest() {
			y -= 1;
			int temp = cells[3];
			cells[3] = cells[0];
			cells[0] = cells[2];
			cells[2] = cells[5];
			cells[5] = temp;
		}

		void moveSouth() {
			x += 1;
			int temp = cells[5];
			cells[5] = cells[4];
			cells[4] = cells[0];
			cells[0] = cells[1];
			cells[1] = temp;
		}

		void moveNorth() {
			x -= 1;
			int temp = cells[1];
			cells[1] = cells[0];
			cells[0] = cells[4];
			cells[4] = cells[5];
			cells[5] = temp;
		}
	}

	private static void init() {
		parents = new int[N * M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				parents[i * M + j] = -1;
			}
		}
	}

	private static int find(int a) {
		if(parents[a] < 0) return a;
		return parents[a] = find(parents[a]);
	}

	private static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);

		if(rootA == rootB) return false;

		parents[rootA] += parents[rootB];
		parents[rootB] = rootA;
		return true;
	}
}
