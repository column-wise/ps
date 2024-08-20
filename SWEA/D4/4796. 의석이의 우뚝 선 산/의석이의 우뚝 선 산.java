import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++) {
			int n = sc.nextInt();
			int[] mountains = new int[n];
			
			for(int i = 0; i < n; i++) {
				mountains[i] = sc.nextInt();
			}
			
			int i = 0;
			int result = 0;
			while(i < n-1) {
				int upCnt = 0;
				int downCnt = 0;
				while(i<n-1 && mountains[i] < mountains[i+1]) {
					upCnt++;
					i++;
				}
				while(i<n-1 && mountains[i] > mountains[i+1]) {
					downCnt++;
					i++;
				}
				
				result += (upCnt*downCnt);
			}
			
			System.out.println("#"+test_case+" "+result);
			
		}
	}
}
