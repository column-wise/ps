import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++){
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int big = Math.max(M, N);
            int small = Math.min(M, N);

            while(big % small != 0){
                int temp = big % small;
                big = small;
                small = temp;
            }

            int gcd = small;

            boolean found = false;
            int t = 0;
            int times = 0;
            while(t <= M*N/gcd){

                t = M*times + x;
                times++;

                int n = ((t-1) % N) + 1;

                if(n == y){
                    found = true;
                    break;
                }
            }

            if(found){
                System.out.println(t);
            } else{
                System.out.println(-1);
            }

        }
    }
}