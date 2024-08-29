import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int[] parents;
	static Island[] islands;
	static List<Edge> edges;
	
	static void makeSet() {
		parents = new int[N];
		islands = new Island[N];
		edges = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			parents[i] = -1;
		}
	}
	
	static int find(int a) {
		if(parents[a] < 0) return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;
		parents[aRoot] += parents[bRoot];
		parents[bRoot] = aRoot;
		return true;
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
			
			double E = Double.parseDouble((br.readLine()));
			
			for(int i = 0; i < N; i++) {
				for(int j = i+1; j < N; j++) {
//					System.out.println(i+" "+j);
//					System.out.println(islands[i].toString());
//					System.out.println(islands[j].toString());
					edges.add(new Edge(i, j, islands[i].getDistance(islands[j])));
				}
			}

			
			Collections.sort(edges);
			
			
//			for(int i = 0; i < edges.size(); i++) {
//				System.out.println(edges.get(i).toString());
//			}
			
			double cost = 0;
			int count = 0;
			for(Edge e : edges) {
				if(union(e.from, e.to)) {
//					System.out.println(e.toString());
					cost += e.dist*e.dist*E;
					if(++count == N-1) {
						break;
					}
				}
			}
			
			cost = Math.round(cost);
			
			System.out.println("#"+test_case+" "+(long)cost);
		}
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
		int from;
		int to;
		double dist;
		
		public Edge(int from, int to, double dist) {
			this.from = from;
			this.to = to;
			this.dist = dist;
		}

		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", dist=" + dist + "]";
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			int result = 0;
			if(dist-o.dist>0) {
				result = 1;
			}else if(dist-o.dist<0) {
				result = -1;
			}
			return result;
		}
	}
}