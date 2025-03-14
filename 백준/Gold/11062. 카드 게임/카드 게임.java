import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] cards;
    static int[][][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());
            cards = new int[N];
            dp = new int[N][N][2];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) {
                cards[i] = Integer.parseInt(st.nextToken());
                for(int j = 0; j < N; j++) {
                    dp[i][j][0] = -1;
                    dp[i][j][1] = -1;
                }
            }
            choice(0, N-1, true);
            sb.append(dp[0][N-1][0]).append("\n");
        }

        System.out.println(sb);
    }

    private static int choice(int left, int right, boolean isMyTurn) {

        int turn = isMyTurn ? 0 : 1;

        if(dp[left][right][turn] != -1) return dp[left][right][turn];

        if(left == right) {
            if(isMyTurn) {
                dp[left][right][turn] = cards[left];
            } else {
                dp[left][right][turn] = 0;
            }
            return dp[left][right][turn];
        }

        if(left+1 == right) {
            if(isMyTurn) {
                dp[left][right][turn] = Math.max(cards[left], cards[right]);
            } else {
                dp[left][right][turn] = Math.min(cards[left], cards[right]);
            }
            return dp[left][right][turn];
        }

        if(isMyTurn) {
            dp[left][right][turn] = Math.max(cards[left] + choice(left+1, right, !isMyTurn), cards[right] + choice(left, right-1, !isMyTurn));
        } else {
            dp[left][right][turn] = Math.min(choice(left+1, right, !isMyTurn), choice(left, right-1, !isMyTurn));
        }
        return dp[left][right][turn];
    }
}
