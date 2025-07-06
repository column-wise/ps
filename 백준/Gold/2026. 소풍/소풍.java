import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
	static int N, K;
	static List<Integer>[] adjList;
	static boolean[] visited;
	static List<Integer> answer = null;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		int F = Integer.parseInt(st.nextToken());

		adjList = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < F; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjList[a].add(b);
			adjList[b].add(a);
		}

		for (int i = 1; i <= N; i++) {
			List<Integer> group = new ArrayList<>();
			dfs(i, group);
			if (answer != null) break;
		}

		if (answer == null) {
			sb.append("-1\n");
		} else {
			Collections.sort(answer);
			for (int x : answer) {
				sb.append(x).append("\n");
			}
		}

		System.out.println(sb);
	}

	private static void dfs(int cur, List<Integer> group) {
		group.add(cur);
		visited[cur] = true;

		if (group.size() == K) {
			answer = new ArrayList<>(group);
			return;
		}

		for (int next = cur + 1; next <= N; next++) {
			if (visited[next]) continue;

			boolean canAdd = true;
			for (int g : group) {
				if (!adjList[next].contains(g)) {
					canAdd = false;
					break;
				}
			}

			if (canAdd) {
				dfs(next, group);
				if (answer != null) return;
			}
		}

		group.remove(group.size() - 1);
		visited[cur] = false;
	}
}
