import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static final int INF = 10000;
	static int N;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());
			
			int[][] adjMatrix = new int[N][N];
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				
				adjMatrix[a][b] = 1;
			}
			
			int[][] dist = new int[N][N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(i == j) continue;
					if(adjMatrix[i][j] == 0) {
						dist[i][j] = INF;
					} else {
						dist[i][j] = 1;
					}
				}
			}
			
			floydWarshall(adjMatrix, dist);
			
			int result = 0;
			
			for(int i = 0; i < N; i++) {
				boolean[] canDetermineHeight = new boolean[N];
				int count = 0;
				
				for(int j = 0; j < N; j++) {
					if(dist[i][j] != INF) canDetermineHeight[j] = true;
				}
				
				for(int j = 0; j < N; j++) {
					if(dist[j][i] != INF) canDetermineHeight[j] = true;
				}
				
				for(int j = 0; j < N; j++) {
					if(canDetermineHeight[j]) count++;
				}
				
				if(count == N) result++;
			}
			
			System.out.println("#"+test_case+" "+result);
		}
	}
	
	private static void floydWarshall(int[][] adjMatrix, int[][] dist) {
		for(int k = 0; k < N; k++) {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k]+dist[k][j]);
				}
			}
		}
	}
}