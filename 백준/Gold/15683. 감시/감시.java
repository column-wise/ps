import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int[][] directions = {{}, {0}, {0, 2}, {0, 1}, {0, 1, 2}, {0, 1, 2, 3}};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int totalCells = N*M;
		int minBlindSpots = totalCells;
		List<CCTV> cctvs = new ArrayList<>();
		int numOfCCTV = 0;

		int[][] initialOffice = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				initialOffice[i][j] = Integer.parseInt(st.nextToken());
				if(0< initialOffice[i][j] && initialOffice[i][j] < 6) {
					cctvs.add(new CCTV(i, j, initialOffice[i][j]));
				}
			}
		}

		numOfCCTV = cctvs.size();
		int cases = 1;
		for(int i = 0; i < numOfCCTV; i++) {
			cases *= 4;
		}

		// CCTV가 바라보는 방향을 쭉 나열해서 4진수처럼 만들어서 루프돌리고싶다
		for(int c = 0; c < cases; c++) {
			int[][] office = new int[N][M];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					office[i][j] = initialOffice[i][j];
				}
			}

			int tmp = c;
			for(int i = 0; i < numOfCCTV; i++) {
				CCTV cctv = cctvs.get(i);
				int x = cctv.x;
				int y = cctv.y;
				int type = cctv.type;
				int view = tmp % 4;

				// right shift 2번 = 나누기 4
				tmp = tmp >> 2;

				for(int d : directions[type]) {
					int direction = (view + d) % 4;

					int nx = x + dx[direction];
					int ny = y + dy[direction];
					while(0<=nx && nx<N && 0<=ny && ny<M) {
						if(office[nx][ny] == 6) break;

						office[nx][ny] += i+10;
						nx += dx[direction];
						ny += dy[direction];
					}
				}
			}
			int total = totalCells;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(office[i][j] != 0) {
						total --;
					}
				}
			}

			minBlindSpots = Math.min(minBlindSpots, total);
		}
		System.out.println(minBlindSpots);
	}

	private static class CCTV {
		int x, y;
		int type;
		CCTV(int x, int y, int type) {
			this.x = x;
			this.y = y;
			this.type = type;
		}
	}
}
