import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        int[] series = new int[N];
        boolean[][] isPalindrome = new boolean[N][N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            series[i] = Integer.parseInt(st.nextToken());
        }

        // 길이 1, 2 팰린드롬 초기화
        for (int i = 0; i < N; i++) {
            isPalindrome[i][i] = true;
            if (i < N - 1 && series[i] == series[i + 1]) {
                isPalindrome[i][i + 1] = true;
            }
        }

        // 길이 3 이상인 팰린드롬 체크
        for (int length = 3; length <= N; length++) {
            for (int start = 0; start <= N - length; start++) {
                int end = start + length - 1;
                if (series[start] == series[end] && isPalindrome[start + 1][end - 1]) {
                    isPalindrome[start][end] = true;
                }
            }
        }

        // 쿼리 처리
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;

            sb.append(isPalindrome[start][end] ? 1 : 0).append("\n");
        }

        System.out.print(sb);
    }
}