import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		int n = Integer.parseInt(br.readLine());
		int[] A = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		int m = Integer.parseInt(br.readLine());
		int[] B = new int[m];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < m; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}

		long[] prefixA = new long[n+1];
		long[] prefixB = new long[m+1];
		Map<Long, Integer> counter = new HashMap<>();
		long answer = 0;

		for(int i = 1; i <= n; i++) {
			prefixA[i] = prefixA[i-1] + A[i-1];
		}

		for(int i = 1; i <= m; i++) {
			prefixB[i] = prefixB[i-1] + B[i-1];
		}

		for(int i = 1; i <= n; i++) {
			for(int j = 0; j < i; j++) {
				long sum = prefixA[i] - prefixA[j];
				if(counter.containsKey(sum)) {
					counter.put(sum, counter.get(sum)+1);
				} else {
					counter.put(sum, 1);
				}
			}
		}

		for(int i = 1; i <= m; i++) {
			for(int j = 0; j < i; j++) {
				long sum = prefixB[i] - prefixB[j];
				long target = T - sum;
				if(counter.containsKey(target)) {
					answer += counter.get(target);
				}
			}
		}

		System.out.println(answer);
	}
}
