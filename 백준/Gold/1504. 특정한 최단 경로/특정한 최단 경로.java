import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static final int INF = 10000000;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<Node>[] graph = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			
			graph[from].add(new Node(to, cost));
			graph[to].add(new Node(from, cost));
		}
		
		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken()) - 1;
		int v2 = Integer.parseInt(st.nextToken()) - 1;
		
		int[] distFromStart = new int[N];
		int[] distFromv1 = new int[N];
		int[] distFromv2 = new int[N];
		
		dijkstra(0, graph, distFromStart);	
		dijkstra(v1, graph, distFromv1);	
		dijkstra(v2, graph, distFromv2);

		
		int sum = Math.min(distFromStart[v1] + distFromv1[v2] + distFromv2[N-1], distFromStart[v2]+distFromv2[v1]+distFromv1[N-1]);
		
		if(sum >= INF) {
			System.out.println(-1);
		} else {
			System.out.println(sum);
		}
	}
	
	private static class Node implements Comparable<Node>{
		int idx;
		int cost;
		
		private Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(cost, o.cost);
		}
	}
	
	private static void dijkstra(int start, List<Node>[] graph, int[] dist) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N];
		
		for(int i = 0; i < N; i++) {
			dist[i] = INF;
		}
		dist[start] = 0;
		pq.offer(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			if(visited[cur.idx]) continue;
			visited[cur.idx] = true;
			for(Node next : graph[cur.idx]) {

				if(dist[next.idx] > dist[cur.idx] + next.cost) {
					dist[next.idx] = dist[cur.idx] + next.cost;  
					pq.offer(new Node(next.idx, dist[next.idx]));
				}
			}
		}
	}
}