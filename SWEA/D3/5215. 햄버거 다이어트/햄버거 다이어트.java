import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static Ingredient [] ingredients;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			ingredients = new Ingredient[n];
			int maxScore = 0;
			
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int score = Integer.parseInt(st.nextToken());
				int calory = Integer.parseInt(st.nextToken());
				ingredients[i] = new Ingredient(score, calory);
			}
			
			for(int i = 0; i <= n; i++) {
				int[] selected = new int[n];
				for(int j = n-1; j >= n-i; j--) {
					selected[j] = 1;
				}
				
				do {
//					for(int j = 0; j < n; j++) {
//						System.out.print(selected[j]+" ");
//					}
//					System.out.println();
					int scoreSum = 0;
					int calorySum = 0;
					for(int j = 0; j < n; j++) {
						if(selected[j] == 1) {
							calorySum += ingredients[j].calory;
							if(calorySum > l) {
								break;
							}
							scoreSum += ingredients[j].score;
						}
					}
					if (calorySum <= l) {
	                    maxScore = Math.max(maxScore, scoreSum);
	                }
				}while(nextPermutation(selected));
				
			}
			System.out.println("#"+test_case+" "+maxScore);
		}
	}
	
	public static boolean nextPermutation(int[] selected) {
		int N = selected.length;
		int i = N-1;
		
		// 꼭대기(i) 찾기
		while(i>0 && selected[i-1]>=selected[i])i--;
		if(i==0)return false;
		
		int j = N-1;
		while(selected[j]<=selected[i-1])j--;
		swap(selected, i-1, j);
		
		int k = N-1;
		while(i < k) {
			swap(selected, i++, k--);
		}
		
		return true;
	}
	
	public static void swap(int[] selected, int a, int b) {
		int temp = selected[a];
		selected[a] = selected[b];
		selected[b] = temp;
	}
	
	public static class Ingredient{
		int score;
		int calory;
		
		public Ingredient(int score, int calory) {
			this.score = score;
			this.calory = calory;
		}
	}
}