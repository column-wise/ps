import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	static List<Edge>[] edgeList;
	static int V;
	static int E;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= t; test_case++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			edgeList = new ArrayList[V];
			for(int i = 0; i < V; i++) {
				edgeList[i] = new ArrayList<>();
			}
			
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				int cost = Integer.parseInt(st.nextToken());
				
				edgeList[a].add(new Edge(b, cost));
				edgeList[b].add(new Edge(a, cost));
			}
			
			System.out.println("#"+test_case+" "+Prim(0));
		}
	}
	
	public static long Prim(int startVertex) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(startVertex, 0));
		boolean visited[] = new boolean[V];
		
		Edge e;
		long totalCost = 0;
		while(!pq.isEmpty()) {
			e = pq.poll();
			if(!visited[e.to]) {
				visited[e.to] = true; 
				totalCost += e.cost;
				
				for(Edge next : edgeList[e.to]) {
					if(!visited[next.to]) {
						pq.add(next);
					}
				}
			}
		}
		
		return totalCost;
	}
	
	public static class Edge implements Comparable<Edge>{
		int to;
		int cost;
		
		public Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return cost - o.cost;
		}
		
	}
	
}