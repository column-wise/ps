import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] profits = new int[N+1][M];
        int[][] dp = new int[N+1][M];
        String[][] tracking = new String[N+1][M];
        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            for (int j = 0; j < M; j++) {
                profits[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1; i < N+1; i++) {
            dp[i][0] = profits[i][0];
            tracking[i][0] = ""+i;
        }

        tracking[0][0] = "0";
        for (int j = 1; j < M; j++) {
            tracking[0][j] = tracking[0][j-1] + " 0";
        }

        for(int j = 1; j < M; j++) {
            for(int i = 1; i < N+1; i++) {
                int max = 0;
                String track = "";
                for(int k = 0; k <= i; k ++) {
                    if(max < dp[k][j-1] + profits[i-k][j]) {
                        max = dp[k][j-1] + profits[i-k][j];
                        track = tracking[k][j-1] + " " + (i-k);
                    }
                }
                dp[i][j] = max;
                tracking[i][j] = track;
            }
        }

        System.out.println(dp[N][M-1]);
        System.out.println(tracking[N][M-1]);
    }
}
