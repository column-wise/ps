import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static final long INF = Long.MAX_VALUE/4;
	static List<Edge>[] adjList;
	static long[] fox;
	static long[][] wolf;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		adjList = new ArrayList[N+1];
		fox = new long[N+1];
		wolf = new long[N+1][2];

		for(int i = 0; i <= N; i++) {
			adjList[i] = new ArrayList<>();
			fox[i] = INF;
			wolf[i][0] = INF;
			wolf[i][1] = INF;
		}


		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken()) * 2;

			adjList[from].add(new Edge(to, cost));
			adjList[to].add(new Edge(from, cost));
		}

		dijkstraFox();
		dijkstraWolf();

		int count = 0;
		for(int i = 1; i <= N; i++) {
			if(fox[i] < Math.min(wolf[i][0], wolf[i][1])) count++;
		}

		System.out.println(count);
	}

	private static void dijkstraFox() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		fox[1] = 0;

		pq.add(new Node(1, 0));

		while(!pq.isEmpty()) {
			Node node = pq.poll();
			int cur = node.cur;
			long sum = node.sum;

			if(sum != fox[cur]) continue;

			for(Edge edge : adjList[cur]) {
				if(fox[edge.to] > sum + edge.cost) {
					fox[edge.to] = sum + edge.cost;
					pq.add(new Node(edge.to, sum + edge.cost));
				}
			}
		}
	}

	private static void dijkstraWolf() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		wolf[1][0] = 0;

		pq.add(new Node(1, 0));
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			int cur = node.cur;
			long sum = node.sum;
			boolean isPrevFast = node.isPrevFast;
			
			if(isPrevFast) {
				if(sum != wolf[cur][1]) continue;
			} else {
				if(sum != wolf[cur][0]) continue;
			}

			for(Edge edge : adjList[cur]) {
				if(isPrevFast) {
					long nextCost = sum + (long) edge.cost * 2;
					if(wolf[edge.to][0] > nextCost) {
						wolf[edge.to][0] = nextCost;
						pq.add(new Node(edge.to, nextCost));
					}
				} else {
					long nextCost = sum + edge.cost / 2;
					if(wolf[edge.to][1] > nextCost) {
						wolf[edge.to][1] = nextCost;
						pq.add(new Node(edge.to, nextCost, true));
					}
				}
			}
		}
	}

	private static class Edge {
		int to;
		int cost;

		Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}

	private static class Node implements Comparable<Node> {
		int cur;
		long sum;
		boolean isPrevFast;

		Node(int cur, long sum) {
			this(cur, sum, false);
		}

		Node(int cur, long sum, boolean isPrevFast) {
			this.cur = cur;
			this.sum = sum;
			this.isPrevFast = isPrevFast;
		}

		public int compareTo(Node o) {
			return Long.compare(this.sum, o.sum);
		}
	}
}
