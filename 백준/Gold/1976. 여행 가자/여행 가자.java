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
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		List<Integer>[] adjList = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}

		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				int link = Integer.parseInt(st.nextToken());
				if(link == 1) {
					adjList[i].add(j);
				}
			}
		}

		st = new StringTokenizer(br.readLine());
		int[] dests = new int[M];
		for(int i = 0; i < M; i++) {
			dests[i] = Integer.parseInt(st.nextToken());
		}

		int start = dests[0];
		boolean[] visited = new boolean[N+1];
		visited[start] = true;
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(start);

		while(!queue.isEmpty()) {
			int cur = queue.poll();
			for(int next : adjList[cur]) {
				if(!visited[next]) {
					visited[next] = true;
					queue.add(next);
				}
			}
		}

		for(int i = 0; i < M; i++) {
			if(!visited[dests[i]]) {
				System.out.println("NO");
				return;
			}
		}

		System.out.println("YES");
	}
}
