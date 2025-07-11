import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		List<Edge>[] adjList;
		Queue<Node> queue = new ArrayDeque<>();
		int[] visited;

		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			adjList = new List[n+1];
			visited = new int[n+1];
			for(int i = 1; i <= n; i++) {
				adjList[i] = new ArrayList<>();
				visited[i] = Integer.MAX_VALUE;
			}

			for(int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				int to = Integer.parseInt(st.nextToken());
				int from = Integer.parseInt(st.nextToken());
				int time = Integer.parseInt(st.nextToken());

				adjList[from].add(new Edge(to, time));
			}

			queue.add(new Node(c, 0));
			visited[c] = 0;

			while(!queue.isEmpty()) {
				Node node = queue.poll();
				int cur = node.n;
				int time = node.time;

				for(Edge e : adjList[cur]) {
					int next = e.to;
					if(visited[next] > time + e.time) {
						visited[next] = time + e.time;
						queue.add(new Node(next, time + e.time));
					}
				}
			}

			int count = 0;
			int max = 0;
			for(int i = 1; i <= n; i++) {
				if(visited[i] != Integer.MAX_VALUE) {
					count++;
					max = Math.max(max, visited[i]);
				}
			}

			sb.append(count).append(" ").append(max).append("\n");
		}
		System.out.println(sb);
	}

	private static class Edge {
		int to;
		int time;

		Edge(int to, int time) {
			this.to = to;
			this.time = time;
		}
	}

	private static class Node {
		int n;
		int time;

		Node(int n, int time) {
			this.n = n;
			this.time = time;
		}
	}
}
