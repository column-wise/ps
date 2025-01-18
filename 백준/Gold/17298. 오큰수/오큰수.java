import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N+1];
        int[] NGE = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            NGE[i] = -1;
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(1);

        for(int i = 2; i <= N; i++) {
            if(stack.isEmpty() || arr[stack.peek()] < arr[i]) {
                while(!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                    NGE[stack.pop()] = arr[i];
                }
            }
            stack.push(i);
        }

        for(int i = 1; i <= N; i++) {
            sb.append(NGE[i]).append(" ");
        }

        System.out.println(sb);
    }
}