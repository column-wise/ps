import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for(int test_case = 1; test_case <= T; test_case ++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			int [][] matrix = new int[n][n];
			int [][] prefixSum = new int[n+1][n+1];
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++) {
					matrix[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int max = 0;
			
			prefixSum[1][1] = matrix[0][0];
			
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <= n; j++) {
					prefixSum[i][j] = prefixSum[i-1][j] + prefixSum[i][j-1] - prefixSum[i-1][j-1] + matrix[i-1][j-1];
				}
			}
			
			for(int i = m; i < n+1; i++) {
				for(int j = m; j < n+1; j++) {
					max = Math.max(max,  prefixSum[i][j] - prefixSum[i][j-m] - prefixSum[i-m][j] + prefixSum[i-m][j-m]);
				}
			}
			
			System.out.println("#"+test_case+  " " + max);
			
		}
		
	}
}