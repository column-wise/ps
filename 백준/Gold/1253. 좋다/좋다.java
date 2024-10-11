import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        boolean[] isGood = new boolean[N];
        Arrays.sort(arr);

        for(int i = 0; i < N; i++){
            int target = arr[i];

            int left = 0;
            int right = N - 1;
            while(left < right){
                if(left == i) {
                    left++;
                    continue;
                }
                if(right == i) {
                    right--;
                    continue;
                }

                if(arr[left] + arr[right] < target){
                    left++;
                } else if(arr[left] + arr[right] > target){
                    right--;
                } else{
                    isGood[i] = true;
                    break;
                }
            }
        }

        int count = 0;
        for(int i = 0; i < N; i++){
            if(isGood[i]){
                count++;
            }
        }
        System.out.println(count);
    }
}