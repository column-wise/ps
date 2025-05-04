import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<Integer>[] graph;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		graph = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int boss = Integer.parseInt(st.nextToken());
			if(boss == -1) continue;
			graph[boss].add(i);
		}

		System.out.println(dfs(0));
	}

	private static int dfs(int root) {
		int max = 0;
		List<Node> nodes = new ArrayList<>();
		for(int i = 0; i < graph[root].size(); i++) {
			int id = graph[root].get(i);
			nodes.add(new Node(id, dfs(id)));
		}

		nodes.sort((n1, n2) -> n2.time - n1.time);
		for(int i = 0; i < nodes.size(); i++) {
			max = Math.max(max, nodes.get(i).time + i  + 1);
		}

		return max;
	}

	private static class Node {
		int id;
		int time;

		Node (int id, int time) {
			this.id = id;
			this.time = time;
		}
	}
}