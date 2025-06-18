import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static List<Edge>[] adjList;
	static int[] dist;
	static int N;
	static final int INF = 50_000_000;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		init();

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			adjList[from].add(new Edge(to, weight));
			adjList[to].add(new Edge(from, weight));
		}

		dijkstra(1, N);

		System.out.println(dist[N]);
	}

	private static class Edge {
		int to;
		int weight;

		Edge(int to, int weight) {
				this.to = to;
				this.weight = weight;
		}
	}

	private static void init() {
		adjList = new List[N+1];
		dist = new int[N+1];
		for(int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
			dist[i] = INF;
		}
	}

	private static void dijkstra(int start, int end) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.add(start);
		dist[start] = 0;

		while(!pq.isEmpty()) {
			int n = pq.poll();

			for(Edge edge : adjList[n]) {
				if(dist[edge.to] > dist[n] + edge.weight) {
					dist[edge.to] = dist[n] + edge.weight;
					pq.add(edge.to);
				}
			}
		}
	}
}
