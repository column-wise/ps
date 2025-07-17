import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		List<Integer>[] adjList = new List[N+1];
		List<Node> notBuilt = new LinkedList<>();
		int[] times = new int[N+1];
		int[] indegree = new int[N+1];

		for(int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}

		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			notBuilt.add(new Node(i, time));
			while(st.hasMoreTokens()) {
				int required = Integer.parseInt(st.nextToken());
				if(required != -1) {
					adjList[required].add(i);
					indegree[i]++;
				}
				else break;
			}
		}

		while(!notBuilt.isEmpty()) {
			Iterator<Node> it = notBuilt.iterator();
			while(it.hasNext()) {
				Node n = it.next();
				if(indegree[n.id] == 0) {
					times[n.id] += n.time;
					for(int next : adjList[n.id]) {
						indegree[next] --;
						times[next] = Math.max(times[next], times[n.id]);
					}
					it.remove();
				}
			}
		}

		for(int i = 1; i <= N; i++) {
			sb.append(times[i]).append("\n");
		}
		System.out.println(sb);
	}

	private static class Node {
		int id;
		int time;

		Node(int id, int time) {
			this.id = id;
			this.time = time;
		}
	}
}
