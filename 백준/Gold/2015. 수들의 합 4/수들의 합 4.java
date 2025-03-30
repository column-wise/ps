import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Map<Long, Long> prefixCount = new HashMap<>();
        long prefixSum = 0;
        long result = 0;

        prefixCount.put(0L, 1L);

        for (int i = 0; i < N; i++) {
            prefixSum += arr[i];
            result += prefixCount.getOrDefault(prefixSum - K, 0L);
            prefixCount.put(prefixSum, prefixCount.getOrDefault(prefixSum, 0L) + 1);
        }

        System.out.println(result);
    }
}
