import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] string = br.readLine().toCharArray();
		char[] pattern = br.readLine().toCharArray();
		
		int[] pi = new int[pattern.length];
		computePi(pi, pattern);
		
		
		int i = 0;
		int j = 0;
		int count = 0;
		List<Integer> idxes = new ArrayList<>();
		
		while(i < string.length) {
			if(string[i] == pattern[j]) {
				i++;
				j++;
			} else if(j!=0){
				j = pi[j-1];
			} else {
				i++;
			}
			
			if(j == pattern.length) {
				count ++;
				idxes.add(i-j+1);
				j = pi[j-1];
			}
		}
		
		System.out.println(count);
		for(int idx : idxes) {
			System.out.println(idx);
		}
	}
	
	private static void computePi(int[] pi, char[] pattern) {
		int M = pattern.length;
		int length = 0;
		int suffixPoint = 1;
		
		while(suffixPoint < M) {
			if(pattern[length] == pattern[suffixPoint]) {
				length++;
				pi[suffixPoint] = length;
				suffixPoint++;
			} else {
				if(length != 0) {
					length = pi[length-1];
				} else {
					pi[suffixPoint] = 0;
					suffixPoint++;
				}
			}
		}
	}
}