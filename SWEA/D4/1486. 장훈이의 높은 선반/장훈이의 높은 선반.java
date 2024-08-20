import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int limit = Integer.parseInt(st.nextToken());
			int min = Integer.MAX_VALUE;
			
			int[] heights = new int[n];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				heights[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = 0; i < (1<<n); i++) {
				int sum = 0;
				for(int j = 0; j < n; j++) {
					if((i & (1<<j)) != 0) {
						sum += heights[j];
					}
				}
				
				if(sum >= limit) {
					min = Math.min(min, sum-limit);
				}
			}
			
			System.out.println("#"+test_case+" "+min);
			
		}
	}
}