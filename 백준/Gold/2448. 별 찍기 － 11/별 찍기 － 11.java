import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer, sb;

        int N = Integer.parseInt(br.readLine());
        int[] repeats = new int[N+1];
        String[] lines = new String[N+1];
        repeats[1] = 1;
        repeats[2] = 1;
        repeats[3] = 1;
        lines[1] = "*";
        lines[2] = "* *";
        lines[3] = "*****";

        int completeTriangleSize = 1;
        int gap = -1;

        for(int i = 4; i <= N; i++) {
            if(gap == -1) {
                gap = completeTriangleSize * 6 - 1;
            }

            sb = new StringBuilder(lines[i-(completeTriangleSize*3)]);
            for(int j = 0; j < gap; j++) {
                sb.append(" ");
            }
            sb.append(lines[i-(completeTriangleSize*3)]);

            lines[i] = sb.toString();
            gap -= 2;

            if(i == completeTriangleSize * 3 * 2) {
                completeTriangleSize *= 2;
            }
        }

        answer = new StringBuilder();
        for(int i = 1; i <= N; i++) {
            sb = new StringBuilder();
            for(int j = 0; j < N-i; j++) {
                sb.append(" ");
            }
            sb.append(lines[i]);
            while(sb.length() < 2*N) {
                sb.append(" ");
            }
            sb.append("\n");
            answer.append(sb);
        }

        System.out.println(answer);
    }
}