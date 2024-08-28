import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[] parents;
	static int n;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb;
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			sb = new StringBuilder();
			sb.append("#"+test_case+" ");
			
			makeSet();
			
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int command = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if(command == 0) {
					union(a,b);
				} else if(command == 1) {
					int rootA = find(a);
					int rootB = find(b);
					
					if(rootA == rootB) {
						sb.append(1);
					} else {
						sb.append(0);
					}
				}
			}
			
			System.out.println(sb);
			
		}
	}
	
	private static void makeSet() {
		parents = new int[n+1];
		for(int i = 0; i < n+1; i++) {
			parents[i] = -1;
		}
	}
	
	private static int find(int a) {
		if(parents[a] < 0) return a;
		return parents[a] = find(parents[a]);
	}
	
	private static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		if(rootA == rootB) return false;
		
		if(a < b) {
			parents[rootA] += parents[rootB];
			parents[rootB] = rootA;
		}else {
			parents[rootB] += parents[rootA];
			parents[rootA] = rootB;
		}
		return true;
	}
}