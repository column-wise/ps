import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int [] prefixSum = new int[n];
        prefixSum[0] = 0;
        int count = 1;
        int left = 0;
        int right = 1;

        for(int i = 1; i < n; i++){
            prefixSum[i] = prefixSum[i-1] + i;
        }
/*
        for(int i = 0; i < n; i++){
            System.out.println(prefixSum[i]);
        }
*/
        while(right < n){
            if(prefixSum[right] - prefixSum[left] < n){
                right++;
            }
            else if(prefixSum[right] - prefixSum[left] > n){
                left++;
            }
            else {
                //System.out.printf("%d, %d\n",prefixSum[right],prefixSum[left]);
                right++;
                left++;
                count++;;
            }
        }

        System.out.println(count);
    }
}
