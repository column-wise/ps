import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++){
            List<Integer> lastDigits = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) % 10;
            int b = Integer.parseInt(st.nextToken());

            if(a == 0){
                sb.append(10).append("\n");
                continue;
            }

            for(int i = 1; i < 10; i++){
                int lastDigit = (int) Math.pow(a,i) % 10;
                if(!lastDigits.contains(lastDigit)){
                    lastDigits.add(lastDigit);
                } else {
                    break;
                }
            }

            int resultIndex = (b - 1) % lastDigits.size();
            sb.append(lastDigits.get(resultIndex)).append("\n");
        }

        System.out.println(sb);
    }
}