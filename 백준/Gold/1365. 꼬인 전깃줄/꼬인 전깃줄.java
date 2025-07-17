import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		List<Integer> lis = new ArrayList<>(N);

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		for(int i = 0; i < N; i++) {
			int idx = -(Collections.binarySearch(lis, arr[i]) + 1);
			if(lis.size() == idx) {
				lis.add(arr[i]);
			} else {
				lis.set(idx, arr[i]);
			}
		}

		System.out.println(N-lis.size());
	}
}
