import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Solution {
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static int[][] mountain;
	static boolean[][] visited;
	static int maxLength;
	static int peakHeight;
	static int N;
	static int K;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			mountain = new int[N][N];
			maxLength = 0;
			peakHeight = 0;
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					mountain[i][j] = Integer.parseInt(st.nextToken());
					if(mountain[i][j] > peakHeight) {
						peakHeight = mountain[i][j];
					}
				}
			}
			
			// 모든 좌표의 높이 1~K 만큼 깎아 보고
			// 각 좌표를 시작점으로 DFS 탐색.
			// 가장 높은 점 peakHeight를 만나면 maxLength와 depth를 비교하여 갱신
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					for(int k = 1; k <= K; k++) {
						visited = new boolean[N][N];
						mountain[i][j] -= k;
						
						for(int r = 0; r < N; r++) {
							for(int c = 0; c < N; c++) {
								visited[r][c] = true;
								dfs(r, c, 1, -20);
								visited[r][c] = false;
							}
						}
						
						mountain[i][j] += k;
					}
				}
			}
			System.out.println("#"+test_case+" "+maxLength);
		}
	}
	
	static void dfs(int x, int y, int depth, int prevHeight) {
		if(mountain[x][y] <= prevHeight) {
			return;
		}
		
		if(mountain[x][y] == peakHeight) {
			maxLength = Math.max(maxLength, depth);
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(0<=nx && nx<N && 0<=ny && ny<N && !visited[nx][ny]) {

				visited[nx][ny] = true;
				dfs(nx, ny, depth+1, mountain[x][y]);
				visited[nx][ny] = false;
			}
		}
		
	}
}
