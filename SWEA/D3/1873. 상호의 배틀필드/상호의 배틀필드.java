import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Solution {
	static int curDirection;
	static int curX;
	static int curY;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static List<Character> tank = Arrays.asList('>', 'v', '<', '^');
	static int H;
	static int W;
	static char[][] map;

	public static void main(String[] args) throws IOException, InterruptedException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {

			String[] inputs = br.readLine().split(" ");
			H = Integer.parseInt(inputs[0]);
			W = Integer.parseInt(inputs[1]);

			map = new char[H][W];
			for (int i = 0; i < H; i++) {
				inputs = br.readLine().split("");
				for (int j = 0; j < W; j++) {
					map[i][j] = inputs[j].charAt(0);
					if (tank.contains(map[i][j])) {
						curX = i;
						curY = j;
						curDirection = tank.indexOf(map[i][j]);
					}
				}
			}

			int N = Integer.parseInt(br.readLine());
			inputs = br.readLine().split("");
			for(int i = 0; i < N; i++) {
				char command = inputs[i].charAt(0);
				switch(command) {
				case 'U':
					move(3);
					break;
				case 'D':
					move(1);
					break;
				case 'L':
					move(2);
					break;
				case 'R':
					move(0);
					break;
				case 'S':
					shoot();
					break;
				}
			}
			
			System.out.print("#"+test_case+" ");
			for(int a = 0; a < H; a++) {
				for(int b = 0; b < W; b++) {
					System.out.print(map[a][b]);
				}
				System.out.println();
			}
		}
	}

	private static void move(int direction) {
		curDirection = direction;
		int nx = curX + dx[curDirection];
		int ny = curY + dy[curDirection];
		map[curX][curY] = tank.get(curDirection);
		
		if (0 > nx || nx >= H || 0 > ny || ny >= W) {
			return;
		}

		if (map[nx][ny] == '.') {
			map[curX][curY] = '.';
			curX = nx;
			curY = ny;
		}
		
		map[curX][curY] = tank.get(curDirection);
	}

	private static void shoot() {
		int shellX = curX + dx[curDirection];
		int shellY = curY + dy[curDirection];

		while (shellX >= 0 && shellX < H && shellY >= 0 && shellY < W) {
			if (map[shellX][shellY] == '*') {
				map[shellX][shellY] = '.';
				return;
			} else if (map[shellX][shellY] == '#') {
				return;
			}
			
			shellX += dx[curDirection];
			shellY += dy[curDirection];
		}
	}

}