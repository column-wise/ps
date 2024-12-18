import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int M = Integer.parseInt(br.readLine());
        long modula = (long) (1e9 + 7);
        long expectSum = 0;

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            // a/b
            long b = Long.parseLong(st.nextToken());
            long a = Long.parseLong(st.nextToken());

            long bInverse = getModulaInverse(b, modula);
            
            expectSum = (expectSum + (a * bInverse) % modula) % modula;
        }

        System.out.println(expectSum);
    }

    private static long getModulaInverse(long b, long modula) {
        long result = 1;
        long exp = modula - 2;
        while(exp > 0) {
            if((exp & 1) == 1) {
                result = (result * b) % modula;
            }
            b = (b*b) % modula;
            exp >>= 1;
        }
        return result;
    }
}