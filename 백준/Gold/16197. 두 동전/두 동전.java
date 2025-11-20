import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int answer = 11;
	static char[][] board;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int ax = -1;
		int ay = -1;
		int bx = -1;
		int by = -1;

		board = new char[N][M];
		for(int i = 0; i < N; i++) {
			char[] inputs = br.readLine().toCharArray();
			for(int j = 0; j < M; j++) {
				if(inputs[j] == 'o') {
					if(ax == -1) {
						ax = i;
						ay = j;
					} else {
						bx = i;
						by = j;
					}

					board[i][j] = '.';
				} else {
					board[i][j] = inputs[j];
				}
			}
		}

		dfs(ax, ay, bx, by, 0);
		System.out.println(answer < 11 ? answer : -1);
	}

	private static void dfs (int ax, int ay, int bx, int by, int depth) {
		if(depth == 10) return;

		for(int i = 0; i < 4; i++) {
			int nax = ax + dx[i];
			int nay = ay + dy[i];
			if(0 <= nax && nax < N && 0 <= nay && nay < M && board[nax][nay] == '#') {
				nax = ax;
				nay = ay;
			}
			int nbx = bx + dx[i];
			int nby = by + dy[i];
			if(0 <= nbx && nbx < N && 0 <= nby && nby < M && board[nbx][nby] == '#') {
				nbx = bx;
				nby = by;
			}

			if(nax == nbx && nay == nby) continue;
			if(ax == nax && ay == nay && bx == nbx && by == nby) continue;

			int fallenCoins = countFallenCoins(nax, nay, nbx, nby);
			if(fallenCoins == 0) {
				dfs(nax, nay, nbx, nby, depth + 1);
			} else if(fallenCoins == 1) {
				answer = Math.min(answer, depth + 1);
				return;
			}
		}
	}

	private static int countFallenCoins (int ax, int ay, int bx, int by) {
		int count = 0;
		if(ax < 0 || ax >= N || ay < 0 || ay >= M) count ++;
		if(bx < 0 || bx >= N || by < 0 || by >= M) count ++;
		return count;
	}
}
