import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static Set<String> membersOfInstitute;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		Map<String, Integer> name2id;
		Node[] institutes;

		int N = Integer.parseInt(br.readLine());
		while(N!=0) {
			String[] inputs = new String[N];
			name2id = new HashMap<>();
			institutes = new Node[N];
			membersOfInstitute = new HashSet<>();
			for(int i = 0; i < N; i++) {
				String line = br.readLine();
				st = new StringTokenizer(line, ":");
				String institute = st.nextToken();
				inputs[i] = st.nextToken();
				name2id.put(institute, i);
				institutes[i] = new Node();
			}

			for(int i = 0; i < N; i++) {
				Node n = institutes[i];
				st = new StringTokenizer(inputs[i], ",.");
				while(st.hasMoreTokens()) {
					String name = st.nextToken();
					if(name2id.containsKey(name)) {
						n.children.add(institutes[name2id.get(name)]);
					} else {
						n.members.add(name);
					}
				}
			}

			institutes[0].traverse();

			sb.append(membersOfInstitute.size()).append("\n");

			N = Integer.parseInt(br.readLine());
		}

		System.out.println(sb);
	}

	private static class Node {
		List<String> members = new ArrayList<>();
		List<Node> children = new ArrayList<>();
		boolean visited = false;

		private void traverse() {
			if(visited) return;
			visited = true;

			membersOfInstitute.addAll(members);

			for(Node n : children) {
				n.traverse();
			}
		}
	}
}
