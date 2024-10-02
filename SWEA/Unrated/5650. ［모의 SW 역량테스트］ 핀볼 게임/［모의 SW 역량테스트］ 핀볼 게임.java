import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {
	// 우 하 좌 상
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static Map<Integer, Wormhole> wormholes;
	static int[][] map;
	static int N;
	static int count;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine().trim());

		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine().trim());

			map = new int[N][N];
			wormholes = new HashMap<>();
			count = 0;
			int max = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());

					if (map[i][j] >= 6) {
						if (wormholes.get(map[i][j]) != null) {
							wormholes.get(map[i][j]).pair = new Wormhole(i, j);
						} else {
							wormholes.put(map[i][j], new Wormhole(i, j));
						}
					}
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {

					if (map[i][j] != 0)
						continue;

					map[i][j] = -1;
					for (int d = 0; d < 4; d++) {
						int nx = i;
						int ny = j;
						int direction = d;
						count = 0;

						while (true) {
							nx = nx + dx[direction];
							ny = ny + dy[direction];

							PinballState newState = handleInteraction(nx, ny, direction);

//							for (int r = 0; r < N; r++) {
//								for (int c = 0; c < N; c++) {
//									if (r == nx && c == ny) {
//										System.out.print("*" + " ");
//									} else {
//										System.out.print(map[r][c] + " ");
//									}
//								}
//								System.out.println();
//							}
//							System.out.println(count);
//							System.out.println(newState.x + " " + newState.y + " " + newState.direction);
//							System.out.println();
//							Thread.sleep(1000);

							if (newState.x == -1 && newState.y == -1 && newState.direction == -1)
								break;

							nx = newState.x;
							ny = newState.y;
							direction = newState.direction;
						}

						max = Math.max(max, count);
					}

					map[i][j] = 0;
				}
			}
		System.out.println("#"+test_case+" "+max);
		}
	}

	static PinballState handleInteraction(int r, int c, int dir) {
		int x = r;
		int y = c;
		int direction = dir;

		if (x < 0 || x > N - 1 || y < 0 || y > N - 1) {
			direction = (direction + 2) % 4;
			count++;
		} else if (map[x][y] == 0) {

		} else if (map[x][y] >= 6) {
			Wormhole w = wormholes.get(map[x][y]);
			if (w.x == x && w.y == y) {
				w = w.pair;
			}
			x = w.x;
			y = w.y;
		} else if (map[x][y] == -1) {
			x = -1;
			y = -1;
			direction = -1;
		} else {
			if (direction == 0) {
				if (map[x][y] == 3) {
					direction = 1;
				} else if (map[x][y] == 4) {
					direction = 3;
				} else {
					direction = 2;
				}
			} else if (direction == 1) {
				if (map[x][y] == 1) {
					direction = 0;
				} else if (map[x][y] == 4) {
					direction = 2;
				} else {
					direction = 3;
				}
			} else if (direction == 2) {
				if (map[x][y] == 1) {
					direction = 3;
				} else if (map[x][y] == 2) {
					direction = 1;
				} else {
					direction = 0;
				}
			} else if (direction == 3) {
				if (map[x][y] == 2) {
					direction = 0;
				} else if (map[x][y] == 3) {
					direction = 2;
				} else {
					direction = 1;
				}
			}
			count++;
		}

		return new PinballState(x, y, direction);
	}

	private static class PinballState {
		int x;
		int y;
		int direction;

		public PinballState(int x, int y, int direction) {
			super();
			this.x = x;
			this.y = y;
			this.direction = direction;
		}
	}

	private static class Wormhole {
		int x;
		int y;
		Wormhole pair;

		public Wormhole(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	private static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}