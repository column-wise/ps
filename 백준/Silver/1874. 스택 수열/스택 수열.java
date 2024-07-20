import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        int source = 1;
        Stack<Integer> stack = new Stack<>();
        boolean flag = true;

        for(int i = 0; i < N; i++){
            int input = Integer.parseInt(br.readLine());
            while(flag) {
                if (!stack.empty() && stack.peek() == input) {
                    stack.pop();
                    result.append("-\n");
                    break;
                } else if(source > N && !stack.empty() && stack.peek() != input){
                    flag = false;
                } else {
                    while (source <= N){
                        stack.push(source);
                        result.append("+\n");
                        source++;

                        if (stack.peek() == input){
                            break;
                        }
                    }
                }
            }
        }

        if(flag){
            System.out.println(result);
        }else {
            System.out.println("NO");
        }

    }
}
