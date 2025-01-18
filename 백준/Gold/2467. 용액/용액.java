import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] liquids = new int[N];
        int liquid1 = 0;
        int liquid2 = 0;
        int liquidSum = Integer.MAX_VALUE;
        boolean found = false;

        for(int i = 0; i < N; i++) {
            liquids[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(liquids);

        for(int i = 0; i < N; i++) {
            int a = liquids[i];

            int left = i + 1;
            int right = N - 1;

            while(left <= right) {
                int mid = (left + right) / 2;

                if(a + liquids[mid] < 0) {
                    left = mid + 1;
                } else if(a + liquids[mid] > 0) {
                    right = mid - 1;
                } else {
                    found = true;
                    liquid1 = a;
                    liquid2 = liquids[mid];
                    break;
                }

                if(Math.abs(a + liquids[mid]) < Math.abs(liquidSum)) {
                    liquidSum = a + liquids[mid];
                    liquid1 = a;
                    liquid2 = liquids[mid];
                }
            }
        }

        System.out.println(liquid1 + " " + liquid2);
    }
}