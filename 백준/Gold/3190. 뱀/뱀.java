import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
	
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	
	public static void main(String[] args) throws IOException, InterruptedException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		
		int[][] map = new int[n+2][n+2];
		
		for(int i = 0; i < n+2; i++){
			map[0][i] = 1;
			map[n+1][i] = 1;
			map[i][0] = 1;
			map[i][n+1] = 1;
		}

		
		for(int i = 0; i < k; i++) {
			String[] inputs = br.readLine().split(" ");
			int c = Integer.parseInt(inputs[0]);
			int r = Integer.parseInt(inputs[1]);
			map[c][r] = 2;
		}
		
		int l = Integer.parseInt(br.readLine());
		
		Deque<Integer[]> directions = new ArrayDeque<>();
		for(int i = 0; i < l; i++) {
			String[] inputs = br.readLine().split(" ");
			int t = Integer.parseInt(inputs[0]);
			String d = inputs[1];
			
			// L이면 -1, D면 1저장
			if(d.equals("L")) {
				directions.add(new Integer[] {t,-1});
			}else {
				directions.add(new Integer[] {t,1});
			}
		}
		
		LinkedList<Integer[]> snake = new LinkedList<>();
		snake.add(new Integer[] {1,1});
		int direction = 0;
		
		int count = 0;
		
		while(true) {
			count ++;
			
			Integer[] head = snake.getFirst();
			int nextX = head[0] + dx[direction];
			int nextY = head[1] + dy[direction];
			if(map[nextX][nextY] == 2) {
				snake.addLast(new Integer[] {0,0});
			} else if(map[nextX][nextY] == 1) {
				break;
			}
			
			for(int i = 0; i < snake.size(); i++) {
				map[snake.get(i)[0]][snake.get(i)[1]] = 0;
			}
			
			for(int i = snake.size()-1; i >0 ; i--) {
				snake.get(i)[0] = snake.get(i-1)[0];
				snake.get(i)[1] = snake.get(i-1)[1];
				map[snake.get(i)[0]][snake.get(i)[1]] = 1;
			}
			snake.getFirst()[0] = nextX;
			snake.getFirst()[1] = nextY;
			map[snake.get(0)[0]][snake.get(0)[1]] = 1;
			
			if(!directions.isEmpty()) {
				if(directions.peek()[0] == count) {
					Integer[] change = directions.poll();
					direction += change[1];
					if(direction<0) {
						direction += 4;
					}
					direction %= 4;
				}
			}
			
//			for(int i = 0; i < n+2; i++) {
//				for(int j = 0; j < n+2; j++) {
//					System.out.print(map[i][j]+" ");
//				}
//				System.out.println();
//			}
//			System.out.println();
//			
//			Thread.sleep(1000);
		}
		
		System.out.println(count);
	}
}