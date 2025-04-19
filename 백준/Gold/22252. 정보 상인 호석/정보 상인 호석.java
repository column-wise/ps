import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		Map<String, PriorityQueue<Integer>> sellers = new HashMap<>();
		long sum = 0;

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int queryType = Integer.parseInt(st.nextToken());
			String seller = st.nextToken();
			int infos = Integer.parseInt(st.nextToken());

			if(queryType == 1) {
				if(!sellers.containsKey(seller)) {
					sellers.put(seller, new PriorityQueue<>((a, b) -> b - a));
				}

				for(int j = 0; j < infos; j++) {
					PriorityQueue<Integer> pq = sellers.get(seller);
					pq.add(Integer.parseInt(st.nextToken()));
				}
			} else {
				try {
					PriorityQueue<Integer> pq = sellers.get(seller);
					for(int j = 0; j < infos; j++) {
						if(pq.isEmpty()) break;
						sum += pq.poll();
					}
				} catch(NullPointerException e) {
					continue;
				}
			}
		}

		System.out.println(sum);
	}
}
