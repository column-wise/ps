import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if(N==1){
            System.out.println(0);
        } else if(N==2){
            System.out.println(1);
        } else {

            long[] dpTable = new long[N + 1];
            dpTable[0] = 0;
            dpTable[1] = 0;
            dpTable[2] = 1;

            for (int i = 3; i < N + 1; i++) {
                dpTable[i] = ((i - 1) * (dpTable[i - 1] + dpTable[i - 2])) % 1000000000;
            }

            System.out.println(dpTable[N]);
        }
    }
}