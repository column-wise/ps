import java.util.List;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	static List<List<Integer>> net;
	static boolean [] virus;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		net = new ArrayList<>();
		virus = new boolean[n];
		virus[0] = true;
		visited = new boolean[n];
		int count = 0;
		StringTokenizer st;
		
		for(int i = 0; i < n; i++) {
			net.add(new ArrayList<Integer>());
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			
			net.get(a).add(b);
			net.get(b).add(a);
		}
		
		for(int i = 0; i < n-1; i++) {
			if(virus[i]==true) {
				DFS(i);
			}
		}
		for(int i = 0; i < n; i++) {
			if(virus[i] == true) {
				count ++;
			}
		}
		System.out.println(count-1);
	}
	public static void DFS(int start) {
		if(visited[start] == true) {
			return;
		} else {
			visited[start] = true;
			virus[start] = true;
			for(int n : net.get(start)) {
				DFS(n);
			}
		}
	}
}
