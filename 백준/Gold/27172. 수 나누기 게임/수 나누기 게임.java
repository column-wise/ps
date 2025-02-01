import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] nums = new int[N];
        int[] score = new int[N];
        int[] devideCount = new int[1000001];
        boolean[] flag = new boolean[1000001];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            flag[nums[i]] = true;
        }

        for(int i = 0; i < N; i++) {
            int n = nums[i];
            int mul = 2;

            while(n * mul < 1000001) {
                if(flag[n * mul]) {
                    score[i] ++;
                    devideCount[n * mul]++;
                }
                mul++;
            }
        }

        for(int i = 0; i < N; i++) {
            System.out.print(score[i] - devideCount[nums[i]] + " ");
        }
    }
}