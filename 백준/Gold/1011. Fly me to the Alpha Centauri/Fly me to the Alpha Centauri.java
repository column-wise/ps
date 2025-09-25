import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			int distance = to - from;
			int root = (int) Math.sqrt(distance);

			if(distance == root * root) {
				sb.append(2*root - 1);
			} else if(distance <= root * root + root) {
				sb.append(2*root);
			} else {
				sb.append(2*root + 1);
			}

			sb.append("\n");
		}
		System.out.println(sb);
	}
}
