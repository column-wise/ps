import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		int[] ids = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			ids[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(ids);
		int left = 0;
		int right = N-1;
		int count = 0;

		while(left < right) {
			int sum = ids[left] + ids[right];
			if(sum == M) {
				left++;
				right--;
				count++;
			} else if(sum > M) {
				right--;
			} else {
				left++;
			}
		}

		System.out.println(count);
	}
}
