import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++){
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int[] sequence = new int[N];
            int[] memo = new int[N+1];
            int max = 0;

            for(int i = 0; i < N; i++){
                sequence[i] = Integer.parseInt(st.nextToken());
            }

            int len = 0;
            for(int i = 0; i < N; i++){
                if(sequence[i] > memo[len]){
                    len++;
                    memo[len] = sequence[i];
                } else{
                    int injectionIdx = -(Arrays.binarySearch(memo, 0, len, sequence[i]) + 1);
                    memo[injectionIdx] = sequence[i];
                }
            }

            System.out.println("#"+test_case+" "+len);
        }
    }
}