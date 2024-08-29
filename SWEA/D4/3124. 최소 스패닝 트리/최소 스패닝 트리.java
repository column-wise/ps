import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static List<Edge> edges;
	static int[] parents;
	static int V;
	static int E;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			makeSet();
			
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken())-1;
				int b = Integer.parseInt(st.nextToken())-1;
				int c = Integer.parseInt(st.nextToken());
				edges.add(new Edge(a,b,c));
			}
			
			Collections.sort(edges);
			int count = 0;
			long cost = 0;
			
			for(Edge e : edges) {
				if(union(e.from, e.to)) {
					cost += e.weight;
					if(++count == V-1) {
						break;
					}
				}
			}
			
			System.out.println("#"+test_case+" "+cost);
			
		}
	}
	
	static class Edge implements Comparable<Edge>{
		int from;
		int to;
		int weight;
		
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return weight - o.weight;
		}
	}
	
	public static void makeSet() {
		parents = new int[V];
		edges = new ArrayList<>();
		for(int i = 0; i < V; i++) {
			parents[i] = -1;
		}
	}
	
	public static int find(int a) {
		if(parents[a] < 0) return a;
		return parents[a] = find(parents[a]);
	}
	
	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;
		parents[aRoot] += parents[bRoot];
		parents[bRoot] = aRoot;
		return true;
	}
}