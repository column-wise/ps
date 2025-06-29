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
		int[] ports = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			ports[i] = Integer.parseInt(st.nextToken());
		}

		List<Integer> lis = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			int port = ports[i];
			int idx = Collections.binarySearch(lis, port);
			idx = -(idx + 1);

			if(idx == lis.size()) lis.add(port);
			else lis.set(idx, port);
		}

		System.out.println(lis.size());
	}
}
