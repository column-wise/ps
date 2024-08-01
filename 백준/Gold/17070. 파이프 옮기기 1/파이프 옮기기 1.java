import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int count = 0;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		int[][] map = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int x1 = 0;
		int y1 = 0;
		int x2 = 0;
		int y2 = 1;
		DFS(map, x1, y1, x2, y2);
		System.out.println(count);
	}
	public static void DFS(int[][] map, int x1, int y1, int x2, int y2) {
		if(x2<0 || x2>=n || y2<0 || y2>=n) {
			return;
		}
		for(int i = x1; i <= x2; i++) {
			for(int j = y1; j <= y2; j++) {
				if(map[i][j] == 1) {
					return;
				}
			}
		}
		if(x2 == n-1 && y2 == n-1) {
			count ++;
			return;
		}
		
		if(x1==x2) {
			DFS(map, x2, y2, x2, y2+1);
			DFS(map, x2, y2, x2+1, y2+1);
		} else if(y1==y2) {
			DFS(map, x2, y2, x2+1, y2);
			DFS(map, x2, y2, x2+1, y2+1);
		} else {
			DFS(map, x2, y2, x2+1, y2+1);
			DFS(map, x2, y2, x2+1, y2);
			DFS(map, x2, y2, x2, y2+1);
		}
		
	}
}
