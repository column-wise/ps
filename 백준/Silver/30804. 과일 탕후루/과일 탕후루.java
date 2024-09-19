import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] tanghuru = new int[n];
        int[] fruits = new int[10];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++) {
            tanghuru[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;
        int max = 0;
        fruits[tanghuru[right]] = 1;

        while(right < n){

            int res = countFruitType(fruits);
            if(res > 0){
                max = Math.max(max, res);
                right++;
                if(right == n) break;
                fruits[tanghuru[right]] ++;
            } else{
                fruits[tanghuru[left]]--;
                left++;
            }
        }
        System.out.println(max);
    }

    // count: 과일 종류
    // sum: 과일 개수 총 count
    // count >= 3이면 음수 리턴
    static int countFruitType(int[] fruits){
        int count = 0;
        int sum = 0;
        for(int i = 0; i < fruits.length; i++) {
            if(fruits[i] > 0) count++;
            sum += fruits[i];
        }
        if(count > 2){
            sum = -sum;
        }
        return sum;
    }
}