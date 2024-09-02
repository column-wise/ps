import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int[] parents;
	static Island[] islands;
	static List<Edge>[] edges;
	static double E;
	
	static void makeSet() {
		islands = new Island[N];
		edges = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			edges[i] = new ArrayList<>();
		}
	}
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			
			makeSet();
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				islands[i] = new Island(i, Integer.parseInt(st.nextToken()));
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				islands[i].y = Integer.parseInt(st.nextToken());
			}
			
			E = Double.parseDouble((br.readLine()));
			
			for(int i = 0; i < N; i++) {
				for(int j = i+1; j < N; j++) {
//					System.out.println(i+" "+j);
//					System.out.println(islands[i].toString());
//					System.out.println(islands[j].toString());
					edges[i].add(new Edge(j, islands[i].getDistance(islands[j])));
					edges[j].add(new Edge(i, islands[j].getDistance(islands[i])));
				}
			}
			

//			for(Edge e : edges[0]) {
//				System.out.print(e.toString()+" ");
//			}


			
			System.out.println("#"+test_case+" "+Math.round(Prim(0)));
		}
	}
	
	static double Prim(int startVertex) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(startVertex, 0));
		boolean[] visited = new boolean[N];
		double totalCost = 0;
		
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			if(!visited[e.to]) {
				visited[e.to] = true;
				totalCost += e.dist*e.dist*E;
				for(Edge next : edges[e.to]) {
					if(!visited[next.to]) {
						pq.add(next);
					}
				}
			}
		}
		return totalCost;
		
	}
	
	static class Island{
		int id;
		int x;
		int y;
		
		@Override
		public String toString() {
			return "Island [id=" + id + ", x=" + x + ", y=" + y + "]";
		}

		public Island(int id, int x) {
			this.id = id;
			this.x = x;
		}
		
		double getDistance(Island i) {
			double H = Math.abs(x-i.x);
			double V = Math.abs(y-i.y);
			
			return Math.sqrt(H*H + V*V);
		}
	}
	
	static class Edge implements Comparable<Edge>{
		int to;
		double dist;
		
		public Edge(int to, double dist) {
			this.to = to;
			this.dist = dist;
		}

		@Override
		public String toString() {
			return "Edge [to=" + to + ", dist=" + dist + "]";
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(dist, o.dist);
		}
	}
}