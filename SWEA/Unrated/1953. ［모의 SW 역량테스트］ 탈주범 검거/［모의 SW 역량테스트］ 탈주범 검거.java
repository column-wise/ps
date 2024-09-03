import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static List<List<Integer>> linkedDirection;
	static List<List<Integer>> linkedStructure;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		linkedDirection = new ArrayList<>();
		linkedDirection.add(Arrays.asList());
		linkedDirection.add(Arrays.asList(0,1,2,3));
		linkedDirection.add(Arrays.asList(1,3));
		linkedDirection.add(Arrays.asList(0,2));
		linkedDirection.add(Arrays.asList(0,3));
		linkedDirection.add(Arrays.asList(0,1));
		linkedDirection.add(Arrays.asList(1,2));
		linkedDirection.add(Arrays.asList(2,3));
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[N][M];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			Deque<Point> queue = new ArrayDeque<>();
			int[][] visited = new int[N][M];
			queue.add(new Point(R,C,1));
			
			while(!queue.isEmpty()) {
				Point p = queue.poll();
				int time = p.arrivedTime;
				if(time > L) break;
				visited[p.x][p.y] = 1;
				for(int direction:linkedDirection.get(map[p.x][p.y])){
					int nx = p.x + dx[direction];
					int ny = p.y + dy[direction];
					
					if(nx >= 0 && nx < N && ny >= 0 && ny < M && visited[nx][ny] == 0) {
						switch(direction) {
						case 0:
							if((map[p.x][p.y] == 1 || map[p.x][p.y] == 3 
							|| map[p.x][p.y] == 4 || map[p.x][p.y] == 5)
									&& (map[nx][ny] == 1 || map[nx][ny] == 3 
									|| map[nx][ny] == 6 || map[nx][ny] == 7)) {
								queue.add(new Point(nx,ny,time+1));
							}
							break;
						case 1:
							if((map[p.x][p.y] == 1 || map[p.x][p.y] == 2 
							|| map[p.x][p.y] == 5 || map[p.x][p.y] == 6)
									&& (map[nx][ny] == 1 || map[nx][ny] == 2 
									|| map[nx][ny] == 4 || map[nx][ny] == 7)) {
								queue.add(new Point(nx,ny,time+1));
							}
							break;
						case 2:
							if((map[p.x][p.y] == 1 || map[p.x][p.y] == 3 
							|| map[p.x][p.y] == 6 || map[p.x][p.y] == 7)
									&& (map[nx][ny] == 1 || map[nx][ny] == 3 
									|| map[nx][ny] == 4 || map[nx][ny] == 5)) {
								queue.add(new Point(nx,ny,time+1));
							}
							break;
						case 3:
							if((map[p.x][p.y] == 1 || map[p.x][p.y] == 2 
							|| map[p.x][p.y] == 4 || map[p.x][p.y] == 7)
									&& (map[nx][ny] == 1 || map[nx][ny] == 2 
									|| map[nx][ny] == 5 || map[nx][ny] == 6)) {
								queue.add(new Point(nx,ny,time+1));
							}
							break;
						}
					}
				}
			}
			int count = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(visited[i][j] == 1) {
						count ++;
					}
				}
			}
			
			System.out.println("#"+test_case + " "+count);
			
		}
	}
	
	private static class Point{
		int x;
		int y;
		int arrivedTime;
		
		private Point(int x, int y, int arrivedTime) {
			this.x = x;
			this.y = y;
			this.arrivedTime = arrivedTime;
		}
	}
	
}
