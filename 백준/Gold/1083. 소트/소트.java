import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] series = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            series[i] = Integer.parseInt(st.nextToken());
        }

        int numOfSwaps = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++){
            int max = series[i];
            int maxIdx = i;
            for(int j = i+1; j <= i+numOfSwaps; j++){
                if(j == n) break;
                if(series[j] > max){
                    max = series[j];
                    maxIdx = j;
                }
            }

            for(int j = maxIdx; j > i; j--){
                int temp = series[j];
                series[j] = series[j-1];
                series[j-1] = temp;
            }
            numOfSwaps -= maxIdx - i;

            if(numOfSwaps == 0) break;
        }

        for(int num : series){
            System.out.print(num +" ");
        }
        System.out.println();

    }
}