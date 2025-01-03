import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Integer> plus = new ArrayList<>();
        List<Integer> minus = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());

            // 0으로 음수 지울 수 있으니 minus에 넣어줘야 함
            if(n > 0) plus.add(n);
            else minus.add(n);
        }

        int sum = 0;
        Collections.sort(plus);
        Collections.sort(minus);

        for(int i = plus.size() - 1; i >= 0; i--) {
            if(i > 0 && plus.get(i) * plus.get(i-1) > plus.get(i) + plus.get(i-1)) {
                sum += plus.get(i) * plus.get(i-1);
                i--;
            } else {
                sum += plus.get(i);
            }
        }

        for(int i = 0; i < minus.size(); i++) {
            if(i < minus.size() - 1 && minus.get(i) * minus.get(i+1) > minus.get(i) + minus.get(i+1)) {
                sum += minus.get(i) * minus.get(i+1);
                i++;
            } else {
                sum += minus.get(i);
            }
        }

        System.out.println(sum);
    }
}