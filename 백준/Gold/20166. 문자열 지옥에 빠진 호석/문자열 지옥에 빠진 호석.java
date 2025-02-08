import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static int N;
    static int M;
    static String[][] field;
    static String[] targets;
    static Map<String, Integer> dict;
    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");

        N = Integer.parseInt(inputs[0]);
        M = Integer.parseInt(inputs[1]);
        int K = Integer.parseInt(inputs[2]);

        field = new String[N][M];
        for(int i = 0; i < N; i++) {
            inputs = br.readLine().split("");
            for(int j = 0; j < M; j++) {
                field[i][j] = inputs[j];
            }
        }

        targets = new String[K];
        for(int i = 0; i < K; i++) {
            targets[i] = br.readLine();
        }
        dict = new HashMap<>();

        for(int i = 0; i < K; i++) {
            if(!dict.containsKey(targets[i])) {
                dict.put(targets[i], 0);
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                permutation(i, j, field[i][j]);
            }
        }

        for(int i = 0; i < K; i++) {
            System.out.println(dict.get(targets[i]));
        }
    }

    private static void permutation(int i, int j, String s) {
        if(dict.containsKey(s)) {
            dict.put(s, dict.get(s) + 1);
        }

        if(s.length() == 5) return;

        for(int d = 0; d < 8; d++) {
            int ni = (i + dx[d] + N) % N;
            int nj = (j + dy[d] + M) % M;

            permutation(ni, nj, s+field[ni][nj]);
        }
    }
}