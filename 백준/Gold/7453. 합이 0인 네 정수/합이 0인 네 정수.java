import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());

		int[] A = new int[N];
		int[] B = new int[N];
		int[] C = new int[N];
		int[] D = new int[N];

		long answer = 0;
		int[] AB = new int[N*N];
		int[] CD = new int[N*N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			A[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
			C[i] = Integer.parseInt(st.nextToken());
			D[i] = Integer.parseInt(st.nextToken());
		}

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				AB[i*N+j] = A[i] + B[j];
				CD[i*N+j] = -C[i] - D[j];
			}
		}

		Arrays.sort(AB);
		Arrays.sort(CD);

		for(int i = 0; i < N*N; i++) {
			int target = AB[i];
			int low = lowerBound(CD, target);
			int high = upperBound(CD, target);

			answer += high - low;
		}

		System.out.println(answer);
	}

	private static int upperBound(int[] array, int target) {
		int left = 0;
		int right = array.length - 1;

		while(left <= right) {
			int mid = (left + right) / 2;
			if(array[mid] <= target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		return left;
	}

	private static int lowerBound(int[] array, int target) {
		int left = 0;
		int right = array.length - 1;
		while(left <= right) {
			int mid = (left + right) / 2;
			if(array[mid] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		return left;
	}
}
