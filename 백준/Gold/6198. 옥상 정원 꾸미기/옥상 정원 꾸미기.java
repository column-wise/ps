import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		long answer = 0;
		int N = Integer.parseInt(br.readLine());
		int[] canViewed = new int[N];
		Stack<Integer> stack = new Stack<>();
		for(int i = 0; i < N; i++) {
			int height = Integer.parseInt(br.readLine());
			while(!stack.isEmpty() && stack.peek() <= height) {
				stack.pop();
			}

			canViewed[i] = stack.size();
			stack.push(height);
		}

		for(int i = 0; i < N; i++) {
			answer += canViewed[i];
		}
		System.out.println(answer);
	}
}
