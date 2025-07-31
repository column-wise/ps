import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuffer sb = new StringBuffer();

		int[] arr = new int[N];
		int[] dp = new int[N];
		int[] prev = new int[N];

		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			dp[i] = 1;
			prev[i] = -1;
		}

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < i; j++) {
				if(arr[i] > arr[j]) {
					if(dp[i] < dp[j] + 1) {
						dp[i] = dp[j] + 1;
						prev[i] = j;
					}
				}
			}
		}

		int maxLength = 0;
		int maxIdx = -1;
		for(int i = 0; i < N; i++) {
			if(dp[i] > maxLength) {
				maxLength = dp[i];
				maxIdx = i;
			}
		}

		sb.append(maxLength).append("\n");
		List<Integer> lis = new ArrayList<>();

		while(prev[maxIdx] != -1) {
			lis.add(arr[maxIdx]);
			maxIdx = prev[maxIdx];
		}
		lis.add(arr[maxIdx]);

		Collections.reverse(lis);
		for(int a : lis) {
			sb.append(a).append(" ");
		}

		System.out.println(sb);
	}
}
