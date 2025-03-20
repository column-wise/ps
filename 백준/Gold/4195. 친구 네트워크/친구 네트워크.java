import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int[] parents;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());

            init(N);

            Map<String, Integer> map = new HashMap<>();
            int id = 1;

            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                String friend1 = st.nextToken();
                String friend2 = st.nextToken();

                if(!map.containsKey(friend1)) {
                    map.put(friend1, id++);
                }

                if(!map.containsKey(friend2)) {
                    map.put(friend2, id++);
                }

                union(map.get(friend1), map.get(friend2));
                sb.append(-parents[find(map.get(friend1))]).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void init(int N) {
        parents = new int[N*2 + 1];
        for(int i = 0; i < N*2 + 1; i++) {
            parents[i] = -1;
        }
    }

    private static int find(int a) {
        if(parents[a] < 0) return a;
        return parents[a] = find(parents[a]);
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return false;

        parents[aRoot] += parents[bRoot];
        parents[bRoot] = aRoot;
        return true;
    }
}