import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int[] parents;
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken())-1;
			int to = Integer.parseInt(st.nextToken())-1;
			int weight = Integer.parseInt(st.nextToken());

			pq.add(new Edge(from, to, weight));
		}

		int sum = 0;
		init();

		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			if(union(e.from, e.to)) sum+=e.weight;
		}

		System.out.println(sum);
	}

	private static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int weight;

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(weight, o.weight);
		}

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
	}

	private static void init() {
		parents = new int[N];
		for(int i = 0; i < N; i++) {
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
		parents[rootA] += parents[rootB];
		parents[rootB] = rootA;

		return true;
	}
}
