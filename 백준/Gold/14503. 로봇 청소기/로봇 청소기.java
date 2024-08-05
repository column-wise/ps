import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int direction = Integer.parseInt(st.nextToken());
		
		int[] directions = {0,1,2,3};
		int[] dx = {-1,0,1,0};
		int[] dy = {0,1,0,-1};
		
		int[][] map = new int[n][m];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int count = 0;
		
		while(true) {
			if(map[x][y] == 0) {
				map[x][y] = -1;
				count++;
			}
			
			if(map[x-1][y]!=0 && map[x+1][y] != 0 && map[x][y-1] != 0 && map[x][y+1] != 0) {
				if(map[x-dx[direction]][y-dy[direction]] == 1) {
					break;
				} else {
					x -= dx[direction];
					y -= dy[direction];
				}
				
			} else {
				direction = (direction-1);
				if(direction<0) {
					direction+=4;
				}
				if(map[x+dx[direction]][y+dy[direction]] == 0) {
					x += dx[direction];
					y += dy[direction];
				}
			}
			
		}
		
		System.out.println(count);
		
	}
}
