import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int period = Integer.parseInt(st.nextToken());

        int[] prefixSum = new int[n+1];
        int max = 0;
        int count = 0;

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            prefixSum[i] = prefixSum[i-1] + Integer.parseInt(st.nextToken());
        }

        for(int i = period; i <= n; i++){
            int visitors = prefixSum[i] - prefixSum[i-period];
            if(max < visitors){
                max = visitors;
                count = 1;
            } else if(max == visitors){
                count++;
            }
        }

        if(max != 0){
            System.out.println(max);
            System.out.println(count);
        } else{
            System.out.println("SAD");
        }

    }
}