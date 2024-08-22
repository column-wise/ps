import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int[] prices;
    static int[] plans;
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++){
            st = new StringTokenizer(br.readLine());
            prices = new int[4];
            for(int i = 0; i < 4; i++){
                prices[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            plans = new int[12];
            for(int i = 0; i < 12; i++){
                plans[i] = Integer.parseInt(st.nextToken());
            }

            min = prices[3];
            optimize(0, 0);

            System.out.println("#"+test_case+" "+min);
        }
    }

    private static void optimize(int priceSum, int month){
        if(month >= 12){
            min = Math.min(min, priceSum);
        } else{
            if(priceSum > min){
                return;
            }
            optimize(priceSum + prices[0] * plans[month], month + 1);
            optimize(priceSum + prices[1] * (plans[month]!=0?1:0), month + 1);
            optimize(priceSum + prices[2], month + 3);
            
        }
    }

}