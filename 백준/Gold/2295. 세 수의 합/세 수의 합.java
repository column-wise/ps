import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] seq = new int[N];

        for(int i = 0; i < N; i++){
            seq[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(seq);

        for(int i = N-1; i >= 0; i--){
            int target = seq[i];
            for(int j = i-1; j >= 0; j--){
                int b = seq[j];
                for(int k = j; k >= 0; k--){
                    int c = seq[k];

                    int start = 0;
                    int end = k;
                    int mid;

                    while(start<=end){
                        mid = (start+end) / 2;
                        int a = seq[mid];

                        if(a + b + c < target){
                            start = mid+1;
                        } else if (a + b + c > target) {
                            end = mid-1;
                        } else {
                            System.out.println(target);
                            return;
                        }
                    }
                }
            }
        }
    }
}