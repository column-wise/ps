import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());

			Node[] nodes = new Node[N+1];
			for(int i = 1; i <= N; i++) {
				nodes[i] = new Node(i);
			}

			for(int i = 1; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int parent = Integer.parseInt(st.nextToken());
				int child = Integer.parseInt(st.nextToken());
				nodes[parent].children.add(nodes[child]);
				nodes[child].parent = nodes[parent];
			}

			st = new StringTokenizer(br.readLine());
			Node a = nodes[Integer.parseInt(st.nextToken())];
			Node b = nodes[Integer.parseInt(st.nextToken())];

			Set<Integer> aAncestors = new HashSet<>();
			Node cur = a;
			while (cur != null) {
				aAncestors.add(cur.n);
				cur = cur.parent;
			}

			cur = b;
			while (cur != null) {
				if (aAncestors.contains(cur.n)) {
					System.out.println(cur.n);
					break;
				}
				cur = cur.parent;
			}
		}
	}

	private static class Node {
		int n;
		Node parent;
		Set<Node> children;

		public Node(int n) {
			this.n = n;
			children = new HashSet<>();
		}
	}
}
