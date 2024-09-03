import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Solution {
	static int N;
	static int map[][];
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				char[] ch = br.readLine().toCharArray();
				for(int j = 0; j < N; j++) {
					map[i][j] = ch[j] - '0';
				}
			}
			
			System.out.println("#"+test_case+" "+getMinTime(0,0,N-1,N-1));
		}
	}
	
	private static int getMinTime(int sr, int sc, int er, int ec) {
		final int INF = Integer.MAX_VALUE;
		boolean[][] visited = new boolean[N][N];
		int[][] minTime = new int[N][N];
		
		// r, c, time
		PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->Integer.compare(a[2],b[2]));
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				minTime[i][j] = INF;
			}
		}
		
		minTime[sr][sc] = 0;
		pq.add(new int[] {sr, sc, 0});
		
		while(!pq.isEmpty()) {
			int[] minVertex = pq.poll();
			int r = minVertex[0];
			int c = minVertex[1];
			int cost = minVertex[2];
			
			if(visited[r][c]) continue;
			visited[r][c] = true;
			if(r == er && c == ec) {
				return cost;
			}
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dx[i];
				int nc = c + dy[i];
				if(nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]
						&& minTime[nr][nc] > map[nr][nc] + cost) {
					minTime[nr][nc] = map[nr][nc] + cost;
					pq.offer(new int[] {nr, nc, minTime[nr][nc]});
				}
			}
		}
		return -1;
	}
	
}