import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int size = 0;
		PriorityQueue<Homework> pq = new PriorityQueue<>();

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int deadline = Integer.parseInt(st.nextToken());
			int reward = Integer.parseInt(st.nextToken());
			size = Math.max(size, deadline);
			pq.add(new Homework(deadline, reward));
		}

		int[] rewards = new int[size+1];
		int score = 0;

		while(!pq.isEmpty()) {
			Homework h = pq.poll();
			int d = h.deadline;
			int r = h.reward;

			for(int i = d; i > 0; i--) {
				if(rewards[i] == 0) {
					rewards[i] = r;
					break;
				}
			}
		}

		for(int i = 0; i <= size; i++) {
			score += rewards[i];
		}

		System.out.println(score);
	}

	private static class Homework implements Comparable<Homework> {
		int deadline;
		int reward;

		Homework(int deadline, int reward) {
			this.deadline = deadline;
			this.reward = reward;
		}

		public int compareTo(Homework o) {
			int ret = Integer.compare(o.reward, this.reward);
			if(ret == 0) ret = Integer.compare(o.deadline, this.deadline);
			return ret;
		}
	}
}
