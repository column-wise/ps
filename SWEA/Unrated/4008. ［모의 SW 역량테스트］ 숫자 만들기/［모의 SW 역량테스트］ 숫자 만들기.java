import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			int n = Integer.parseInt(br.readLine());
			int[] operators = new int[n-1];
			int[] operands = new int[n];
			int idx = 0;
			
			int max = Integer.MIN_VALUE;
			int min = Integer.MAX_VALUE;
			
			st = new StringTokenizer(br.readLine());
			int j = Integer.parseInt(st.nextToken());
			for(int i = 0; i < j; i++) {
				operators[idx] = 0;
				idx++;
			}
			
			j = Integer.parseInt(st.nextToken());
			for(int i = 0; i < j; i++) {
				operators[idx] = 1;
				idx++;
			}
			
			j = Integer.parseInt(st.nextToken());
			for(int i = 0; i < j; i++) {
				operators[idx] = 2;
				idx++;
			}
			
			j = Integer.parseInt(st.nextToken());
			for(int i = 0; i < j; i++) {
				operators[idx] = 3;
				idx++;
			}
			
			st = new StringTokenizer(br.readLine());
			
			for(int i = 0; i < n; i++) {
				operands[i] = Integer.parseInt(st.nextToken());
			}
			
			do {
				int result = operands[0];
				
				for(int i = 0; i < n-1; i++) {
					switch(operators[i]) {
					case 0:
						result += operands[i+1];
						break;
					case 1:
						result -= operands[i+1];
						break;
					case 2:
						result *= operands[i+1];
						break;
					case 3:
						result /= operands[i+1];
						break;
					}
				}
				
				max = Math.max(max, result);
				min = Math.min(min, result);
				
			}while(nextPermutation(operators));
			
			System.out.println("#"+test_case+" "+(max-min));
		}
	}
	
	public static boolean nextPermutation(int[] operators) {
		int N = operators.length;
		int i = N-1;
		
		// 꼭대기(i) 찾기
		while(i>0 && operators[i-1]>=operators[i])i--;
		if(i==0)return false;
		
		int j = N-1;
		while(operators[j]<=operators[i-1])j--;
		swap(operators, i-1, j);
		
		int k = N-1;
		while(i < k) {
			swap(operators, i++, k--);
		}
		
		return true;
	}
	
	public static void swap(int[] operators, int a, int b) {
		int temp = operators[a];
		operators[a] = operators[b];
		operators[b] = temp;
	}
}