import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String initialStr = br.readLine();
        String targetStr = br.readLine();

        boolean[] case1 = new boolean[N];
        boolean[] case2 = new boolean[N];
        boolean[] target = new boolean[N];

        for(int i = 0; i < N; i++) {
            if(initialStr.charAt(i) == '1') {
                case1[i] = true;
                case2[i] = true;
            }

            if(targetStr.charAt(i) == '1') {
                target[i] = true;
            }
        }

        case2[0] = !case2[0];
        case2[1] = !case2[1];

        int count1 = 0;
        int count2 = 1;

        for(int i = 0; i < N-1; i++) {
            if(case1[i] != target[i]) {
                case1[i] = !case1[i];
                case1[i+1] = !case1[i+1];
                if(i < N-2) {
                    case1[i+2] = !case1[i+2];
                }
                count1++;
            }
        }

        for(int i = 0; i < N-1; i++) {
            if(case2[i] != target[i]) {
                case2[i] = !case2[i];
                case2[i+1] = !case2[i+1];
                if(i < N-2) {
                    case2[i+2] = !case2[i+2];
                }
                count2++;
            }
        }

        for(int i = 0; i < N; i++) {
            if(case1[i] != target[i]) {
                count1 = Integer.MAX_VALUE;
            }

            if(case2[i] != target[i]) {
                count2 = Integer.MAX_VALUE;
            }
        }

        int res = Math.min(count1, count2);

        System.out.println(res == Integer.MAX_VALUE ? -1 : res);
    }
}