import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int[] prices = new int[4];
			
			for(int i = 0; i < 4; i++) {
				prices[i] = Integer.parseInt(st.nextToken());
			}
			
			int min = prices[3];
			
			st = new StringTokenizer(br.readLine());
			int[] plan = new int[12];
			for(int i = 0; i < 12; i++) {
				plan[i] = Integer.parseInt(st.nextToken());
			}
			
			int[] dpTable = new int[15];
			for(int i = 0; i < 12; i++) {
				dpTable[i+3] = dpTable[i+2] + plan[i]*prices[0];
				dpTable[i+3] = Math.min(dpTable[i+3], dpTable[i+2]+prices[1]);
				dpTable[i+3] = Math.min(dpTable[i+3], dpTable[i]+prices[2]);
			}
			
			min = Math.min(min, dpTable[14]);
			System.out.println("#"+test_case + " "+min);
		}
	}
}