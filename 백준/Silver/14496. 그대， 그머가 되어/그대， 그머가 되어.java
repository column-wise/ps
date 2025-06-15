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
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		List<Integer>[] adjList = new ArrayList[N+1];
		boolean[] visited = new boolean[N+1];
		Queue<Node> q = new ArrayDeque<>();
		int answer = -1;

		for(int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			adjList[from].add(to);
			adjList[to].add(from);
		}

		q.add(new Node(a, 0));
		visited[a] = true;

		while(!q.isEmpty()) {
			Node n = q.poll();
			int cur = n.cur;
			int times = n.times;

			if(cur == b) {
				answer = times;
				break;
			}

			for(int next : adjList[cur]) {
				if(!visited[next]) {
					visited[next] = true;
					q.add(new Node(next, times + 1));
				}
			}
		}

		System.out.println(answer);
	}

	private static class Node {
		int cur;
		int times;

		public Node (int cur, int times) {
			this.cur = cur;
			this.times = times;
		}
	}
}
