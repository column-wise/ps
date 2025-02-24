import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] w;
    static boolean[][] result;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());


        w = new int[n];
        result = new boolean[n+1][40001];
        for(int i=0; i<n; i++) {
            int src = Integer.parseInt(st.nextToken());
            w[i] = src;
        }

        dp(0,0);

        int c = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<c; i++) {
            int t = Integer.parseInt(st.nextToken());

            if(result[n][t]) {
                sb.append("Y ");
            }else {
                sb.append("N ");
            }
        }

        System.out.println(sb.toString());
    }

    static void dp(int cnt, int num) {
        if(result[cnt][num]) return;
        result[cnt][num] = true;

        if(cnt == n	) return;

        dp(cnt+1, num+ w[cnt]);
        dp(cnt+1, num);
        dp(cnt+1, Math.abs(num- w[cnt]));

    }
}