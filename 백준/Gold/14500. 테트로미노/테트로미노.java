import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static int N, M;
	static int max;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		max = 0;
		
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				visited[i][j] = true;
				dfs(i, j, 1, 4, map[i][j]);
				visited[i][j] = false;
			}
		}
		
		// 0: ㅏ / 1: ㅜ / 2: ㅓ / 3: ㅗ
		boolean canMake[] = null;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				canMake = new boolean[] {true, true, true, true};
				if(i == 0) {
					canMake[0] = false;
					canMake[2] = false;
					canMake[3] = false;
				}
				
				if(i == N-1) {
					canMake[0] = false;
					canMake[1] = false;
					canMake[2] = false;
				}
				
				if(j == 0) {
					canMake[1] = false;
					canMake[2] = false;
					canMake[3] = false;
				}
				
				if(j == M-1) {
					canMake[0] = false;
					canMake[1] = false;
					canMake[3] = false;
				}				
				
				int sum = 0;
				
				if(canMake[0] == true) {
					sum = map[i-1][j] + map[i][j] + map[i][j+1] + map[i+1][j];
					max = Math.max(max, sum);
					sum = 0;
				}
				
				if(canMake[1] == true) {
					sum = map[i][j-1] + map[i][j] + map[i][j+1] + map[i+1][j];
					max = Math.max(max, sum);
					sum = 0;
				}
				
				if(canMake[2] == true) {
					sum = map[i-1][j] + map[i][j] + map[i][j-1] + map[i+1][j];
					max = Math.max(max, sum);
					sum = 0;
				}
				
				if(canMake[3] == true) {
					sum = map[i-1][j] + map[i][j] + map[i][j+1] + map[i][j-1];
					max = Math.max(max, sum);
				}
				
			}
		}
		
		System.out.println(max);
		
	}
	
	private static void dfs(int x, int y, int depth, int target, int sum) {
		if(depth == target) {
			max = Math.max(max, sum);
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx<0 || nx>=N || ny<0 || ny>=M) continue;
			if(visited[nx][ny]) continue;
			
			visited[nx][ny] = true;
			dfs(nx, ny, depth+1, target, sum+map[nx][ny]);
			visited[nx][ny] = false;
		}
	}
}