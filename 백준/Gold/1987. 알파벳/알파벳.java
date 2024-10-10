import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class Main {
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static int max;
	static int R;
	static int C;
	static char[][] map;
	static boolean[][] visited;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = br.readLine().split(" ");
		
		R = Integer.parseInt(inputs[0]);
		C = Integer.parseInt(inputs[1]);
		
		map = new char[R][C];
		visited = new boolean[R][C];
		
		for(int i = 0; i < R; i++) {
			char[] row = br.readLine().toCharArray();
			for(int j = 0; j < C; j++) {
				map[i][j] = row[j];
			}
		}
		
		max = 0;
		List<Character> route = new ArrayList<>();
		route.add(map[0][0]);
		dfs(0, 0, route);
		
		System.out.println(max);
	}
	
	private static void dfs(int x, int y, List<Character> route) {
		max = Math.max(max, route.size());
		
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
			if(visited[nx][ny]) continue;
			if(route.contains(map[nx][ny])) continue;
			
			visited[nx][ny] = true;
			route.add(map[nx][ny]);
			dfs(nx, ny, route);
			visited[nx][ny] = false;
			route.remove(route.size() - 1);
		}
	}
	
}