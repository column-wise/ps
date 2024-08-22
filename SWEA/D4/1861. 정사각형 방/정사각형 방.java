import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[][] map;
	static int n;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			boolean[] memo = new boolean[n*n+1];
			
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(checkMove(i,j)) {
						memo[map[i][j]] = true;
					}
				}
			}
			
			int i = 1;
			int idx = -1;
			int max = 0;
			while(i<n*n) {
				if(memo[i]==true) {
					int count = 1;
					int j = 0;
					while(memo[i+j] == true) {
						count ++;
						j++;
					}
					j--;
					if(count > max) {
						idx = i;
						max = count;
					}
					i += j;
				}
				i++;
			}
			
			System.out.println("#" + test_case+" "+idx+" "+max);
		}
	}
	
	public static boolean checkMove(int x, int y) {
		for(int i = 0; i < 4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			
			if(0 <= nx && nx < n && 0 <= ny && ny< n && map[nx][ny] == map[x][y]+1) {
				return true;
			}
		}
		return false;
	}
	
}