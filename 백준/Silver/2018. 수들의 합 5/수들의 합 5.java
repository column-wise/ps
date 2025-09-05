import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] prefixSum = new int[N + 1];
		int n = 0;
		for(int i = 0; i < N; i++) {
			prefixSum[i+1] = prefixSum[i] + ++n;
		}

		int start = 0;
		int count = 0;
		for(int end = 0; end <= N; end++) {
			while(prefixSum[end] - prefixSum[start] > N) {
				start++;
			}

			if(prefixSum[end] - prefixSum[start] == N) {
				count++;
			}
		}

		System.out.println(count);
	}
}
