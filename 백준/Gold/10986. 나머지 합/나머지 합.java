import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		long sum = 0;
		long[] count = new long[M];

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int a = Integer.parseInt(st.nextToken());
			sum = (sum + a) % M;
			count[(int)sum] ++;
		}

		long answer = count[0];
		for (int i = 0; i < M; i++) {
			if (count[i] >= 2) {
				answer += (count[i] * (count[i] - 1)) / 2;
			}
		}

		System.out.println(answer);
	}
}
