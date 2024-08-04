import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        int n = Integer.parseInt(inputs[0]);
        int m = Integer.parseInt(inputs[1]);
        int [][] matrix = new int[n][m];
        int [][] dp = new int[n+1][m+1];

        for(int i = 0; i < n; i++){
            inputs = br.readLine().split("");
            for(int j = 0; j < m; j++){
                matrix[i][j] = Integer.parseInt(inputs[j]);
            }
        }

        int ans = 0;
        dp[1][1] = matrix[0][0];

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                if(matrix[i-1][j-1] == 1){
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                }
                ans = Math.max(ans, dp[i][j]);
            }
        }
//        for(int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }

        System.out.println(ans*ans);
    }
}
