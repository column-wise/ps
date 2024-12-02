import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int H = Integer.parseInt(input[1]);

        int[] top = new int[N/2];
        int[] bottom = new int[N/2];

        for(int i = 0; i < N; i++) {
            if(i % 2 == 0) {
                bottom[i/2] = Integer.parseInt(br.readLine());
            } else {
                top[i/2] = Integer.parseInt(br.readLine());
            }
        }

        Arrays.sort(bottom);
        Arrays.sort(top);

        int[] crushed = new int[H+1];
        for(int i = 1; i < H+1; i++) {

            int bottomIdx = Arrays.binarySearch(bottom, i);
            int topIdx = Arrays.binarySearch(top, H-i+1);

            if(bottomIdx < 0) {
                crushed[i] += N/2 - ((bottomIdx + 1) * (-1));
            } else {
                // binarySearch의 return 값은 값이 여러 개 있을 때 lowerBound return을 보장하지 않음
                while(bottomIdx > 0 && bottom[bottomIdx-1] == bottom[bottomIdx]) {
                    bottomIdx--;
                }
                crushed[i] += N/2 - (bottomIdx);
            }

            if(topIdx < 0) {
                crushed[i] += N/2 - ((topIdx + 1) * (-1));
            } else {
                while(topIdx > 0 && top[topIdx-1] == top[topIdx]) {
                    topIdx--;
                }
                crushed[i] += N/2 - (topIdx);
            }
        }

        Arrays.sort(crushed);
        int minCrushes = crushed[1];
        int count = 1;

        for(int i = 2; i < H; i++) {
            if(crushed[i] == minCrushes) {
                count ++;
            } else {
                break;
            }
        }

        System.out.println(minCrushes + " " + count);
    }
}