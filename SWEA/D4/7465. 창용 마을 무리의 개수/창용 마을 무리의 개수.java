import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {
	static int [] parents;
	static int N;
	static int count;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			String[] inputs = br.readLine().split(" ");
			N = Integer.parseInt(inputs[0]);
			int m = Integer.parseInt(inputs[1]);
			
			makeSet();
			
			for(int i = 0; i < m; i++) {
				inputs = br.readLine().split(" ");
				int a = Integer.parseInt(inputs[0]);
				int b = Integer.parseInt(inputs[1]);
				union(a, b);
			}
			System.out.println("#"+test_case+" "+count);
		}
	}
	
	public static void makeSet() {
		parents = new int[N+1];
		for(int i = 0; i < N+1; i++) {
			parents[i] = i;
		}
		count = N;
	}
	
	public static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	public static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if(rootA == rootB) return false;
		
		if(a < b) {
			parents[rootB] = rootA;
			count --;
		} else {
			parents[rootA] = rootB;
			count --;
		}
		return true;
	}
}