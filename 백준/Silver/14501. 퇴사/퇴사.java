import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] timeRequired = new int[n];
        int[] pay = new int[n];
        
        // 상담에 필요한 기간은 5일 이하이기 때문
        int[] dp = new int[n+5];

        for(int i = 0; i < n; i++){
            String[] inputs = br.readLine().split(" ");
            timeRequired[i] = Integer.parseInt(inputs[0]);
            pay[i] = Integer.parseInt(inputs[1]);
        }

        for(int i = 0; i < n; i++){
            for(int j = i+timeRequired[i]; j < n+5; j++){
                dp[j] = Math.max(dp[j], dp[i]+pay[i]);
//              System.out.println(j + " " + dp[j]);

            }
//            for(int k = 0; k < n+5; k++){
//                System.out.print(dp[k] + " ");
//            }
//            System.out.println();
        }

        System.out.println(dp[n]);
    }
}
