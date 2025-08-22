import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());

		long big = Math.max(A, B);
		long small = Math.min(A, B);

		long r = big % small;

		while(r != 0) {
			big = small;
			small = r;
			r = big % small;
		}

		for(int i = 0; i < small; i++) {
			sb.append(1);
		}

		System.out.println(sb);
	}
}
