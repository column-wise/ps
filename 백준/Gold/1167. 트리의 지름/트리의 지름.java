import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int farthestVertex;
	static int maxDist;
	static List<Node>[] adjList;
	static boolean[] visited;
	static int V;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		V = Integer.parseInt(br.readLine());
		adjList = new ArrayList[V];
		for(int i = 0; i < V; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < V; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			while(to != -2) {
				int weight = Integer.parseInt(st.nextToken());
				adjList[from].add(new Node(to, weight));
				to = Integer.parseInt(st.nextToken()) - 1;
			}
		}
		
		visited = new boolean[V];
		visited[0] = true;
		farthestVertex = -1;
		maxDist = 0;
		
		dfs(0,0);
		
		visited = new boolean[V];
		visited[farthestVertex] = true;
		maxDist = 0;
		
		dfs(farthestVertex,0);
		
		System.out.println(maxDist);
	}
	
	private static void dfs(int cur, int sum) {
		if(sum > maxDist) {
			maxDist = sum;
			farthestVertex = cur;
		}
		
		for(Node n : adjList[cur]) {
			if(visited[n.to]) continue;
			visited[n.to] = true;
			dfs(n.to, sum+n.weight);
			visited[n.to] = false; 
		}
	}
	
	private static class Node{
		int to;
		int weight;
		
		public Node(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}
	}
}