import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static final int SIZE = 100001;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] dp = new int[SIZE];
		int[] prev = new int[SIZE];
		Queue<Integer> queue = new ArrayDeque<>();

		for(int i = 0; i < SIZE; i++) {
			dp[i] = Integer.MAX_VALUE;
			prev[i] = -1;
		}

		dp[N] = 0;
		queue.add(N);
		while(!queue.isEmpty()) {
			int cur = queue.poll();

			if(cur - 1 >= 0 && dp[cur-1] > dp[cur] + 1) {
				queue.add(cur-1);
				dp[cur-1] = dp[cur] + 1;
				prev[cur-1] = cur;
			}

			if(cur + 1 < SIZE && dp[cur+1] > dp[cur] + 1) {
				queue.add(cur+1);
				dp[cur+1] = dp[cur] + 1;
				prev[cur+1] = cur;
			}

			if(cur * 2 < SIZE && dp[cur*2] > dp[cur] + 1) {
				queue.add(cur*2);
				dp[cur*2] = dp[cur] + 1;
				prev[cur*2] = cur;
			}
		}

		List<Integer> route = new ArrayList<>();
		route.add(K);
		int visitedPoint = K;
		while(prev[visitedPoint] != -1) {
			route.add(prev[visitedPoint]);
			visitedPoint = prev[visitedPoint];
		}

		sb.append(dp[K]).append("\n");
		for(int i = route.size() - 1; i >= 0; i--) {
			sb.append(route.get(i)).append(" ");
		}

		System.out.println(sb);
	}
}
