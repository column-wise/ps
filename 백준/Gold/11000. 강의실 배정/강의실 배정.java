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
		List<Lecture> lectures = new ArrayList<Lecture>();
		PriorityQueue<Lecture> pq = new PriorityQueue<>();
		int maxRooms = 0;

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			lectures.add(new Lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}

		lectures.sort(new Comparator<Lecture>() {
			public int compare(Lecture l1, Lecture l2) {
				return Integer.compare(l1.start, l2.start);
			}
		});

		for(Lecture lecture : lectures) {
			if(pq.isEmpty()) {
				pq.add(lecture);
			} else {
				while(pq.peek().end <= lecture.start) {
					pq.poll();
				}
				pq.add(lecture);
			}
			maxRooms = Math.max(maxRooms, pq.size());
		}
		System.out.println(maxRooms);
	}

	private static class Lecture implements Comparable<Lecture>{
		int start, end;

		Lecture(int start, int end) {
			this.start = start;
			this.end = end;
		}

		public int compareTo(Lecture o) {
			return end - o.end;
		}
	}
}
