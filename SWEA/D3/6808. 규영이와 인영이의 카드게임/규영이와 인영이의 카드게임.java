import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int wins;
	static int loses;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			List<Integer> yourCards = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18));
			List<Integer> myCards = new ArrayList<>();
			List<Integer> permutation = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			
			while(st.hasMoreTokens()) {
				int card = Integer.parseInt(st.nextToken());
				myCards.add(card);
				yourCards.remove(Integer.valueOf(card));
			}
			
			boolean[] isUsed = new boolean[9];
			wins = 0;
			loses = 0;
			
			competition(myCards, yourCards, permutation, isUsed);
			
			System.out.println("#"+test_case+" "+wins+" "+loses);
			
		}
	}
	
	public static void competition(List<Integer> myCards, List<Integer> yourCards, List<Integer> permutation, boolean[] isUsed) {
		if(permutation.size() == 9) {
			int mySum = 0;
			int yourSum = 0;
			for(int i = 0; i < 9; i++) {
				int yours = permutation.get(i);
				int mine = myCards.get(i);
				if(mine > yours) {
					mySum += mine+yours;
				}else {
					yourSum += mine+yours;
				}
			}
			if(mySum > yourSum) {
				wins ++;
			}else {
				loses ++;
			}
		}else {
			for(int i = 0; i < 9; i++) {
				if(!isUsed[i]) {
					permutation.add(yourCards.get(i));
					isUsed[i] = true;
					competition(myCards, yourCards, permutation, isUsed);
					permutation.remove(permutation.size()-1);
					isUsed[i] = false;
				}
			}
		}
	}
}
