import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static char[] inst = {'D', 'S', 'L', 'R'};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		StringTokenizer st;
		Queue<Node> queue;
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			boolean[] visited = new boolean[10000];
			int[] prev = new int[10000];
			char[] how = new char[10000];

			queue = new ArrayDeque<>();
			visited[from] = true;
			prev[from] = -1;
			queue.add(new Node(from));

			while(!queue.isEmpty()) {
				Node node = queue.poll();
				int value = node.register;

				if(value == to) break;

				for(int i = 0; i < 4; i++) {
					int next = operation(value, i);
					if(!visited[next]) {
						visited[next] = true;
						prev[next] = value;
						how[next] = inst[i];
						queue.add(new Node(next));
					}
				}
			}

			int cur = to;
			StringBuilder route = new StringBuilder();
			while(prev[cur] != -1) {
				route.append(how[cur]);
				cur = prev[cur];
			}

			answer.append(route.reverse()).append("\n");
		}

		System.out.println(answer);
	}

	private static class Node {
		int register;

		public Node(int value) {
			register = value;
		}
	}

	private static int operation(int value, int i) {
		if(i == 0) return D(value);
		if(i == 1) return S(value);
		if(i == 2) return L(value);
		if(i == 3) return R(value);
		return -1;
	}

	private static int D(int value) {
		return value * 2 % 10000;
	}

	private static int S(int value) {
		return (value + 9999) % 10000;
	}

	private static int L(int value) {
		return (value % 1000) * 10 + value / 1000;
	}

	private static int R(int value) {
		return (value % 10) * 1000 + value / 10;
	}
}
