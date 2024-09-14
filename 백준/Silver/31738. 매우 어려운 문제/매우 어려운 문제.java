import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Long.parseLong(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        if(N>=M){
            System.out.println(0);
        } else{
            long factorial = 1;
            for(int i = 2; i <= N; i++){
                factorial *= i;
                factorial %= M;
                if(factorial == 0) break;
            }
            System.out.println(factorial);
        }

    }
}