import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	static Cell[][] grid;
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		grid = new Cell[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				grid[i][j] = new Cell(i, j);
			}
		}

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			grid[r][c].queue.add(new FireBall(m, d, s));
		}

		for(int i = 0; i < K; i++) {
			move();
		}

		int answer = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				Queue<FireBall> q = grid[i][j].queue;
				while(!q.isEmpty()) {
					FireBall b = q.poll();
					answer += b.mass;
				}
			}
		}
		System.out.println(answer);
	}

	private static class FireBall {
		int mass;
		int direction;
		int speed;
		boolean moved = false;

		public FireBall(int mass, int direction, int speed) {
			this.mass = mass;
			this.direction = direction;
			this.speed = speed;
		}
	}

	private static class Cell {
		int r;
		int c;
		Queue<FireBall> queue;

		public Cell(int r, int c) {
			this.r = r;
			this.c = c;
			queue = new ArrayDeque<>();
		}

		public void moveFireBalls() {
			while(!queue.isEmpty() && !queue.peek().moved) {
				FireBall fb = queue.poll();
				fb.moved = true;
				int move = fb.speed % N;
				int nr = (r + dx[fb.direction] * move + N) % N;
				int nc = (c + dy[fb.direction] * move + N) % N;

				grid[nr][nc].queue.add(fb);
			}
		}

		public void mergeAndDivideFireBalls() {
			int size = queue.size();
			if(size == 0) return;

			if(size == 1) {
				FireBall fb = queue.poll();
				fb.moved = false;
				queue.add(fb);
				return;
			}

			int massSum = 0;
			int speedSum = 0;
			boolean sameParity = true;
			boolean prevIsOdd = queue.peek().direction % 2 == 1;

			while(!queue.isEmpty()) {
				FireBall fb = queue.poll();
				massSum += fb.mass;
				speedSum += fb.speed;
				boolean isOdd = fb.direction % 2 == 1;
				if(prevIsOdd != isOdd) {
					sameParity = false;
				}
				prevIsOdd = isOdd;
			}

			int direction = sameParity ? 0 : 1;
			int mass = massSum / 5;
			int speed = speedSum / size;

			if(mass != 0) {
				for(int i = 0; i < 4; i++) {
					queue.add(new FireBall(mass, direction, speed));
					direction += 2;
				}
			}
		}
	}

	public static void move() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				grid[i][j].moveFireBalls();
			}
		}

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				grid[i][j].mergeAndDivideFireBalls();
			}
		}
	}
}
