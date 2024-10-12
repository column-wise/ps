import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int A;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        System.out.println(solve(B, 0));
    }

    private static int solve(int n, int depth){
        if(n == A){
            return depth + 1;
        }
        int ret = -1;
        if(n != 0 && n%2 == 0){
            ret = solve(n/2, depth+1);
        }
        if(n%10 == 1){
            ret = solve((n-1)/10, depth+1);
        }
        return ret;
    }
}