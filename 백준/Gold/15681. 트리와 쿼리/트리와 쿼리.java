import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<Integer>[] adjList;
	static Node[] nodes;
	static int N, R;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		nodes = new Node[N+1];
		adjList = new List[N+1];
		for(int i = 1; i <= N; i++) {
			nodes[i] = new Node(i);
			adjList[i] = new ArrayList<>();
		}

		for(int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			adjList[u].add(v);
			adjList[v].add(u);
		}

		nodes[R].init(-1);
		nodes[R].traverse();

		for(int i = 0; i < Q; i++) {
			int q = Integer.parseInt(br.readLine());
			sb.append(nodes[q].subNodes).append("\n");
		}

		System.out.println(sb);
	}

	private static class Node {
		int n;
		int subNodes = 1;
		List<Node> children = new ArrayList<>();

		private Node(int n) {
			this.n = n;
		}

		public void init(int parent) {
			for(int child : adjList[n]) {
				if(child == parent) continue;
				nodes[n].children.add(nodes[child]);
				nodes[child].init(n);
			}
		}

		public int traverse() {
			for(Node child : children) {
				subNodes += child.traverse();
			}

			return subNodes;
		}
	}
}
