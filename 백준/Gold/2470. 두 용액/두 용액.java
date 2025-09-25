import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		int[] liquids = new int[N];
		for(int i = 0; i < N; i++) {
			liquids[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(liquids);

		int left = 0;
		int right = N-1;
		int liquidA = 0;
		int liquidB = 0;
		int min = Integer.MAX_VALUE;

		while(left < right) {
			int a = liquids[left];
			int b = liquids[right];

			if(Math.abs(a + b) < min) {
				liquidA = a;
				liquidB = b;
				min = Math.abs(a + b);
			}

			if(a + b == 0) {
				break;
			} else if(a + b < 0) {
				left++;
			} else {
				right--;
			}
		}

		System.out.println(liquidA + " " + liquidB);
	}
}
