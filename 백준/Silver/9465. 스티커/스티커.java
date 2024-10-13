import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++){
            int N = Integer.parseInt(br.readLine());
            int[][] stickers = new int[2][N];
            int[][] dp = new int[2][N+1];

            for(int i = 0; i < 2; i++){
                st = new StringTokenizer(br.readLine(), " ");
                for(int j = 0; j < N; j++){
                    stickers[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dp[0][1] = stickers[0][0];
            dp[1][1] = stickers[1][0];

            for(int i = 2; i <= N; i++){
                dp[0][i] = Math.max(Math.max(dp[0][i-2], dp[1][i-2]) + stickers[0][i-1], dp[1][i-1] + stickers[0][i-1]);
                dp[1][i] = Math.max(Math.max(dp[0][i-2], dp[1][i-2]) + stickers[1][i-1], dp[0][i-1] + stickers[1][i-1]);
            }

            sb.append(Math.max(dp[0][N], dp[1][N])).append("\n");
        }
        System.out.println(sb);
    }

}