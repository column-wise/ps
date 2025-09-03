import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static Set<Integer>[] afters;
	static List<Integer>[] adjList;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		afters = new Set[n+1];
		adjList = new List[n+1];
		for(int i = 1; i <= n; i++) {
			afters[i] = new HashSet<>();
			adjList[i] = new ArrayList<>();
		}

		for(int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int prev = Integer.parseInt(st.nextToken());
			int next = Integer.parseInt(st.nextToken());
			adjList[prev].add(next);
		}

		for(int i = 1; i <= n; i++) {
			bfs(i);
		}

		int s = Integer.parseInt(br.readLine());
		for(int i = 0; i < s; i++) {
			st = new StringTokenizer(br.readLine());
			int prev = Integer.parseInt(st.nextToken());
			int next = Integer.parseInt(st.nextToken());

			if(afters[prev].contains(next)) {
				sb.append(-1).append("\n");
			} else if(afters[next].contains(prev)){
				sb.append(1).append("\n");
			} else {
				sb.append(0).append("\n");
			}
		}
		System.out.println(sb);
	}

	private static void bfs(int start) {
		boolean[] visited = new boolean[n+1];
		Queue<Integer> queue = new ArrayDeque<>();

		queue.add(start);
		visited[start] = true;

		while(!queue.isEmpty()) {
			int cur = queue.poll();

			for(int next : adjList[cur]) {
				if(!visited[next]) {
					visited[next] = true;
					queue.add(next);
					afters[start].add(next);
				}
			}
		}
	}
}
