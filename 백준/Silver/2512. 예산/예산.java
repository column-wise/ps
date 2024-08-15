import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int [] requests = new int[n];
        int sum = 0;
        int max = 0;

        for(int i = 0; i < n; i++){
            requests[i] = Integer.parseInt(st.nextToken());
            sum += requests[i];
            max = Math.max(max, requests[i]);
        }

        int total = Integer.parseInt(br.readLine());

        if(sum <= total){
            System.out.println(max);
        }else{
            int left = 0;
            int right = max;
            int mid;
            int diff = total;
            while(true) {
                mid = (left + right) / 2;
                sum = 0;
                for (int i = 0; i < n; i++) {
                    if (requests[i] <= mid) {
                        sum += requests[i];
                    } else {
                        sum += mid;
                    }
                }
                if (sum > total) {
                    right = mid;
                } else{
                    if(total - sum == diff){
                        break;
                    }
                    left = mid;
                    diff = total - sum;
                }
            }
            System.out.println(mid);
        }
    }
}
