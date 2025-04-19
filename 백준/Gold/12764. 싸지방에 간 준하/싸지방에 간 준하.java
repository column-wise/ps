import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		List<Node> nodes = new ArrayList<>();
		int[] users = new int[N+1];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		PriorityQueue<Integer> emptySeats = new PriorityQueue<>();
		int min = 0;

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			nodes.add(new Node(start, end));
			emptySeats.add(i+1);
		}

		nodes.sort(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return Integer.compare(o1.start, o2.start);
			}
		});

		for(Node node : nodes){
			while(!pq.isEmpty() && pq.peek().end < node.start) {
				Node n = pq.poll();
				emptySeats.add(n.seat);
			}

			pq.add(node);
			node.seat = emptySeats.poll();
			users[node.seat] ++;
			min = Math.max(pq.size(), min);
		}

		System.out.println(min);
		for(int i = 1; i <= min; i++) {
			System.out.print(users[i] + " ");
		}
	}

	private static class Node implements Comparable<Node> {
		int start;
		int end;
		int seat;

		public Node (int start, int end) {
			this.start = start;
			this.end = end;
		}

		public int compareTo (Node o) {
			return end - o.end;
		}
	}
}
