import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static final int INF = 100000;
	static int N;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			int[][] adjMatrix = new int[N][N];
			int[][] dist = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					adjMatrix[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(i == j) {
						dist[i][j] = 0;
					} else if(adjMatrix[i][j] == 1){
						dist[i][j] = 1;
					} else {
						dist[i][j] = INF;
					}
				}
			}
			
			floydWarshal(adjMatrix, dist);
			int min = INF;
			for(int i = 0; i < N; i++) {
				int count = 0;
				for(int j = 0; j < N; j++) {
					count += dist[i][j];
				}
				min = Math.min(min, count);
			}
			System.out.println("#"+test_case+" "+min);
			
		}
	}
	
	private static void floydWarshal(int[][] adjMatrix, int[][] dist) {
		for(int k = 0; k < N; k++) {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k]+dist[k][j]);
				}
			}
		}
	}
}