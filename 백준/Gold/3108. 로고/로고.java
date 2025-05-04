import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int[] parents;
	static Rect[] rects;
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		rects = new Rect[N+1];
		rects[0] = new Rect(0, 0, 0, 0);

		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			rects[i] = new Rect(x1, y1, x2, y2);
		}

		init();

		for(int i = 0; i <= N; i++) {
			for(int j = i+1; j <= N; j++) {
				if(overlap(rects[i], rects[j])) {
					union(i, j);
				}
			}
		}

		for(int i = N; i >= 0; i--) {
			for(int j = N; j >= 0; j--) {
				if(overlap(rects[i], rects[j])) {
					union(i, j);
				}
			}
		}

		Set<Integer> set = new HashSet<>();
		for(int i = 0; i <= N; i++) {
			set.add(parents[i]);
		}

		System.out.println(set.size() - 1);
	}

	private static class Rect {
		int x1, y1, x2, y2;

		Rect(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
	}

	private static boolean overlap(Rect a, Rect b) {
		boolean overlap = !(a.x2 < b.x1 || b.x2 < a.x1 || a.y2 < b.y1 || b.y2 < a.y1);
		boolean isAInside = a.x1 > b.x1 && a.x2 < b.x2 && a.y1 > b.y1 && a.y2 < b.y2;
		boolean isBInside = a.x1 < b.x1 && a.x2 > b.x2 && a.y1 < b.y1 && a.y2 > b.y2;

		return overlap && !isAInside && !isBInside;
	}

	private static void init() {
		parents = new int[N+1];
		for(int i = 0; i <= N; i++) {
			parents[i] = i;
		}
	}

	private static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}

	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;

		parents[bRoot] = aRoot;
		return true;
	}
}
