import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] seq = new int[N];
        int[] sumOfTwoNums = new int[((N) * (N+1))/2];

        for(int i = 0; i < N; i++){
            seq[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(seq);

        int idx=0;
        for(int i = 0; i < N; i++){
            for(int j = i; j < N; j++){
                sumOfTwoNums[idx++] = seq[i]+seq[j];
            }
        }
        Arrays.sort(sumOfTwoNums);

        // a + b + c = d를 만족하는 (a+b) 값이 sumOfTwoNums에 있는지 탐색
        for(int i = N-1; i >= 0; i--){
            int d = seq[i];
            for(int j = i-1; j >= 0; j--){
                int c = seq[j];

                int start = 0;
                int end = sumOfTwoNums.length - 1;
                int mid;

                while(start<=end){
                    mid = (start + end) / 2;
                    if(sumOfTwoNums[mid] + c < d){
                        start = mid + 1;
                    } else if(sumOfTwoNums[mid] + c > d){
                        end = mid - 1;
                    } else {
                        System.out.println(d);
                        return;
                    }
                }
            }
        }
    }
}