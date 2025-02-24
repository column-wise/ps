import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] dp;
    static int[] chu;
    static int n;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        chu = new int[n];

        for(int i = 0; i < n; i++) {
            chu[i] = Integer.parseInt(st.nextToken());
        }

        dp = new boolean[n+1][40001];
        setDp(0, 0);

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int gusle;
        for(int i = 0; i < m; i++) {
            gusle = Integer.parseInt(st.nextToken());
            try {
                sb.append(dp[n][gusle] ? "Y " : "N ");
            } catch(Exception e) {
                sb.append("N" );
            }
        }

        System.out.println(sb.toString());
    }

    private static void setDp (int i, int weight) {
        if(dp[i][weight]) return;
        dp[i][weight] = true;

        if(i == n) return;

        setDp(i + 1, weight + chu[i]);
        setDp(i + 1, weight);
        setDp(i + 1, Math.abs(chu[i] - weight));
    }
}