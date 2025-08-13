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
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		List<Integer>[] adj = new ArrayList[N];
		int[] indeg = new int[N];
		for (int i = 0; i < N; i++) adj[i] = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			if (cnt == 0) continue;
			int prev = Integer.parseInt(st.nextToken()) - 1;
			for (int k = 1; k < cnt; k++) {
				int curr = Integer.parseInt(st.nextToken()) - 1;
				adj[prev].add(curr);
				indeg[curr]++;
				prev = curr;
			}
		}

		Queue<Integer> q = new ArrayDeque<>();
		for (int i = 0; i < N; i++) if (indeg[i] == 0) q.offer(i);

		int processed = 0;
		while (!q.isEmpty()) {
			int u = q.poll();
			sb.append(u + 1).append('\n');
			processed++;

			for (int v : adj[u]) {
				if (--indeg[v] == 0) q.offer(v);
			}
		}

		if (processed != N) {
			System.out.println(0);
		} else {
			System.out.print(sb);
		}
	}
}
