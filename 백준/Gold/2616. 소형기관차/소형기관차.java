import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] passengers = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			passengers[i] = Integer.parseInt(st.nextToken());
		}

		int maxCars = Integer.parseInt(br.readLine());

		int[] passengersWhenPullCars = new int[N-maxCars+1];

		int start = 0;
		int end = 0;
		int sum = 0;

		while(end < N) {
			sum += passengers[end];
			end++;
			if(end - start == maxCars) {
				passengersWhenPullCars[start] = sum;
				sum -= passengers[start];
				start++;
			}
		}

		// i개 기관차 사용
		// j번째 객차까지 고려
		int[][] dp = new int[4][N+1];

		for(int j = maxCars; j <= N; j++) {
			dp[1][j] = Math.max(passengersWhenPullCars[j-maxCars], dp[1][j-1]);
		}
		
		for(int j = maxCars * 2; j <= N; j++) {
			dp[2][j] = Math.max(dp[1][j-maxCars] + passengersWhenPullCars[j-maxCars], dp[2][j-1]);
		}

		for(int j = maxCars * 3; j <= N; j++) {
			dp[3][j] = Math.max(dp[2][j-maxCars] + passengersWhenPullCars[j-maxCars], dp[3][j-1]);
		}

		System.out.println(dp[3][N]);
	}
}
