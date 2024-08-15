import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        int n = Integer.parseInt(inputs[0]);
        long m = Integer.parseInt(inputs[1]);
        long [] times = new long[n];
        long result = Long.MAX_VALUE;

        for(int i = 0; i < n; i++){
            times[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(times);
        long max = times[n-1];

        long left = 0;
        long right = max * m;
        long mid;
        long sum;

        while(left <= right){

            mid = (left+right)/2;
            sum = 0;

            for(long time_spent : times){
                sum += mid/time_spent;
                if(sum>=m) {
                    break;
                }
            }

            if(sum>=m){
                right = mid-1;
                result = Math.min(result, mid);
            }
            else{
                left = mid +1;
            }

        }

        System.out.println(result);

    }
}