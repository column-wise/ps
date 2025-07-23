import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K;
	static int[][] map;
	static int[][] plus;
	static boolean[][][][] wall;
	// 온풍기 정보
	static List<Pos> machines = new ArrayList<>();
	// 온도를 조사해야하는 좌표 리스트
	static List<Pos> checkList = new ArrayList<>();
	// 온풍기 방향
	static int[] dy = {0, 0, 0, -1, 1};
	static int[] dx = {0, 1, -1, 0, 0};
	//온풍기의 방향에 따른 확산 방향
	static int[][] spreadY = {{0, 0, 0}, {0, -1, 1}, {0, -1, 1}, {-1, -1, -1}, {1, 1, 1}};
	static int[][] spreadX = {{0, 0, 0}, {1, 1, 1}, {-1, -1, -1}, {0, -1, 1}, {0, -1, 1}};

	static class Pos {
		int y, x, dir;

		Pos(int y, int x) {
			this.y = y;
			this.x = x;
			this.dir = -1;
		}

		Pos(int y, int x, int dir) {
			this.y = y;
			this.x = x;
			this.dir = dir;
		}
	}

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("a.txt"));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		wall = new boolean[N][M][N][M];
		K = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if (temp == 5) {
					checkList.add(new Pos(i, j));
				} else if (temp != 0) {
					machines.add(new Pos(i, j, temp));
				}
			}
		}

		st = new StringTokenizer(br.readLine());
		int cnt = Integer.parseInt(st.nextToken());
		for (int i = 0; i < cnt; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			y--;
			x--;
			int t = Integer.parseInt(st.nextToken());
			if (t == 0) {
				wall[y][x][y - 1][x] = true;
				wall[y - 1][x][y][x] = true;
			} else {
				wall[y][x][y][x + 1] = true;
				wall[y][x + 1][y][x] = true;
			}
		}
		int choco = 0;
		while (choco <= 100 && !checkTemp()) {
			workMachine();
			adjust();
			choco++;
		}
		System.out.println(choco);
	}

	// 모든 온풍기작동, 온도 상승을 한번에 합산
	private static void workMachine() {
		plus = new int[N][M];
		for (int i = 0; i < machines.size(); i++) {
			work(machines.get(i).y, machines.get(i).x, machines.get(i).dir);
		}

		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				map[y][x] += plus[y][x];
			}
		}
	}

	// 하나의 온풍기 작동
	private static void work(int i, int j, int dir) {
		boolean[][] visit = new boolean[N][M];
		Queue<Pos> q = new LinkedList<>();

		int t = 5;
		int ny = i + dy[dir];
		int nx = j + dx[dir];

		visit[ny][nx] = true;
		plus[ny][nx] += 5;
		q.add(new Pos(ny, nx, 2));

		while (!q.isEmpty()) {
			Pos now = q.poll();
			int y = now.y;
			int x = now.x;
			int dis = now.dir;

			if (dis > 5) continue;

			for (int d = 0; d < 3; d++) {
				ny = y + spreadY[dir][d];
				nx = x + spreadX[dir][d];
				if (!isRange(ny, nx)) continue;
				if (visit[ny][nx]) continue;
				if (isWall(y, x, ny, nx, dir)) continue;
				visit[ny][nx] = true;
				plus[ny][nx] += t - dis + 1;
				q.add(new Pos(ny, nx, dis + 1));
			}
		}
	}

	// 현재 위치 -> 다음 위치의 이동에 대해 벽에 막혔는지 확인
	private static boolean isWall(int y, int x, int ny, int nx, int machineDir) {
		if (y == ny || x == nx) {
			if (wall[y][x][ny][nx]) return true;
		} else {
			// 온풍기 바람 방향이 좌우
			if(machineDir==1||machineDir==2){
				if(wall[y][x][ny][x] || wall[ny][x][ny][nx]) return true;
			}
			// 온풍기 바람 방향이 상하
			else{
				if(wall[y][x][y][nx] ||wall[y][nx][ny][nx])return true;
			}
		}
		return false;
	}

	// 모든 칸에 대해 온도 조절
	private static void adjust() {
		plus = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) continue;
				_adjust(i, j);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] += plus[i][j];
				if (i == 0 || j == 0 || i == N - 1 || j == M - 1) {
					if (map[i][j] > 0) map[i][j] -= 1;
				}
			}
		}
	}

	// 현재 좌표(i, j)에 대해 인접 칸의 온도 조절
	private static void _adjust(int i, int j) {
		for (int d = 1; d < 5; d++) {
			int ny = i + dy[d];
			int nx = j + dx[d];
			if (!isRange(ny, nx)) continue;
			if (wall[i][j][ny][nx]) continue;
			if (map[i][j] > map[ny][nx]) {
				int temp = (int) ((map[i][j] - map[ny][nx]) / 4);
				plus[i][j] -= temp;
				plus[ny][nx] += temp;
			}
		}
	}

	// 조사해야하는 모든 칸이 K이상인지 확인
	private static boolean checkTemp() {
		for (int i = 0; i < checkList.size(); i++) {
			if (map[checkList.get(i).y][checkList.get(i).x] < K) return false;
		}
		return true;
	}

	// 격자 범위를 벗어나는지 확인
	private static boolean isRange(int i, int j) {
		if (i < 0 || j < 0 || i >= N || j >= M) return false;
		else return true;
	}

	private static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}