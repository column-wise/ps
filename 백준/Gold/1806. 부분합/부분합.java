import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] seq = new int[N];
        int[] prefixSum = new int[N+1];
        List<Integer> possibleLengths = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < N; i++) {
            prefixSum[i+1] = prefixSum[i] + seq[i];
        }

        int right = 1;
        int left = 0;
        while(right <= N && left <= right) {
            if(prefixSum[right] - prefixSum[left] >= S) {
                possibleLengths.add(right - left);
                left ++;
            } else {
                right ++;
            }
        }

        if(!possibleLengths.isEmpty()) {
            Collections.sort(possibleLengths);
            System.out.println(possibleLengths.get(0));
        } else {
            System.out.println(0);
        }
    }
}