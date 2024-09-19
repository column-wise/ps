import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static final int INF = 100000;
	static int[][] minDist;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken()) - 1;
		
		int max = 0;
		
		minDist = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(i == j) continue;
				minDist[i][j] = INF;
			}
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			
			minDist[from][to] = cost;
		}
		
		floyd_warshall();
		
		for(int i = 0; i < N; i++) {
			max = Math.max(max, minDist[i][X]+minDist[X][i]);
		}
		
		System.out.println(max);
	}
	
	private static void floyd_warshall() {
		for(int k = 0; k < N; k++) {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					minDist[i][j] = Math.min(minDist[i][j], minDist[i][k]+minDist[k][j]);
				}
			}
		}
	}
}