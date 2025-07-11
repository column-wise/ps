import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		long count = 0;

		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);

		for(int i = 0; i < N - 2; i++) {
			int a = arr[i];
			int left = i + 1;
			int right = N - 1;

			while(left < right) {
				int b = arr[left];
				int c = arr[right];
				int sum = a + b + c;

				if(sum == 0) {
					if(b == c) {
						int n = right - left + 1;
						count += (long)n * (n - 1) / 2;
						break;
					} else {
						int lCnt = 0, rCnt = 0;
						while(left < right && arr[left] == b) {
							lCnt++;
							left++;
						}
						while(right >= left && arr[right] == c) {
							rCnt++;
							right--;
						}
						count += (long)lCnt * rCnt;
					}
				} else if(sum < 0) {
					left++;
				} else {
					right--;
				}
			}
		}

		System.out.println(count);
	}
}
