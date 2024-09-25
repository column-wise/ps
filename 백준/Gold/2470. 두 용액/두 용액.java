import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Long> pHValues = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++){
            pHValues.add(Long.parseLong(st.nextToken()));
        }

        Collections.sort(pHValues);

        long liquid1 = 0;
        long liquid2 = 0;
        long min = Long.MAX_VALUE;

        for(int i = 0; i < N; i++){
            long ph1 = pHValues.get(i);

            int left = 0;
            int right = N-1;

            while(left <= right){
                int mid = (left + right)/2;
                long ph2 = pHValues.get(mid);

                if(Math.abs(ph1 + ph2) < min && ph1 != ph2){
                    min = Math.abs(ph1 + ph2);
                    liquid1 = Math.min(ph1, ph2);
                    liquid2 = Math.max(ph1, ph2);
                }

                if(ph1 + ph2 < 0){
                    left = mid + 1;
                } else if (ph1 + ph2 > 0){
                    right = mid - 1;
                } else{
                    break;
                }

            }
        }
        System.out.println(liquid1 + " " + liquid2);
    }
}