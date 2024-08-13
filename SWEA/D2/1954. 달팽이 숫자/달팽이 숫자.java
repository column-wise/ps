import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			int n = Integer.parseInt(br.readLine());
			int [][] matrix = new int[n][n];
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					matrix[i][j] = 0;
				}
			}
			
			int direction = 0;
			int num = 1;
			int x = 0;
			int y = 0;
			
			for(int i = 0; i < n*n; i++) {
				matrix[x][y] = num;
				num ++;
				
				if(x+dx[direction] < 0 || x+dx[direction]>=n || y+dy[direction] < 0 || y+dy[direction]>=n) {
					direction += 1;
					direction %= 4;
				}else if(matrix[x+dx[direction]][y+dy[direction]] != 0) {
					direction += 1;
					direction %= 4;
				}
				
				x += dx[direction];
				y += dy[direction];
				
			}
            
            System.out.println("#"+test_case);
			
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					System.out.print(matrix[i][j]+" ");
				}
				System.out.println();
			}
		}
	}
}
