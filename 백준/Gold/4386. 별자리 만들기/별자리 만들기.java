import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int[] parents;
	static int N;
	static int count;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		Star[] stars = new Star[N];
		List<Edge> edges = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			float x = Float.parseFloat(st.nextToken());
			float y = Float.parseFloat(st.nextToken());
			
			stars[i] = new Star(x,y);
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = i+1; j < N; j++) {
				edges.add(new Edge(i, j, stars[i].getDistance(stars[j])));
			}
		}
		
		Collections.sort(edges);
		makeSet();
		
		float minCost = 0;
		int count = 0;
		
		for(int i = 0; i < edges.size(); i++) {
			Edge e = edges.get(i);
			if(union(e.from, e.to)){
				count ++;
				minCost += e.cost;
			}
			
			if(count == N-1) break;
		}
		
		System.out.println(minCost);
		
	}
	
	private static class Star{
		float x;
		float y;
		
		private Star(float x, float y) {
			this.x = x;
			this.y = y;
		}
		
		private float getDistance(Star s) {
			float dx = Math.abs(x - s.x);
			float dy = Math.abs(y - s.y);
			
			return (float) Math.sqrt(dx*dx + dy*dy);
		}
	}
	
	private static class Edge implements Comparable <Edge>{
		int from;
		int to;
		float cost;
		
		private Edge(int from, int to, float cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Float.compare(cost, o.cost);
		}
	}
	
	private static void makeSet() {
		parents = new int[N];
		for(int i = 0; i < N; i++) {
			parents[i] = -1;
		}
		count = 1;
	}
	
	private static int find(int a) {
		if(parents[a] < 0) return a;
		return parents[a] = find(parents[a]);
	}
	
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;
		parents[aRoot] += parents[bRoot];
		parents[bRoot] = aRoot;
		return true;
	}
}