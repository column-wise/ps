import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] liquids = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            liquids[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(liquids);
        long a = 1000000001;
        long b = 1000000001;
        long c = 1000000001;

        for(int i = 0; i < N-2; i++) {
            for(int j = i+1; j < N-1; j++) {
                int left = j+1;
                int right = N-1;

                while(left <= right) {
                    int mid = (left + right) / 2;
                    if(left < N && Math.abs(a+b+c) > Math.abs(liquids[i] + liquids[mid] + liquids[j])) {
                        a = liquids[i];
                        b = liquids[j];
                        c = liquids[mid];
                    }
                    if(liquids[i] + liquids[mid] + liquids[j] > 0) {
                        right = mid - 1;
                    } else if(liquids[i] + liquids[mid] + liquids[j] < 0) {
                        left = mid + 1;
                    } else {
                        System.out.println(liquids[i] + " " + liquids[j] + " " + liquids[mid]);
                        return;
                    }
                }


            }
        }

        System.out.println(a + " " + b + " " + c);
    }
}