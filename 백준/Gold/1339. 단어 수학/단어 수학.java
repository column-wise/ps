import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Map<Character, Integer> map = new HashMap<>();
        int answer = 0;

        for(int i = 0; i < N; i++) {
            char[] inputs = br.readLine().toCharArray();
            int base = 1;
            for(int j = inputs.length - 1; j >= 0; j--) {
                map.put(inputs[j], map.getOrDefault(inputs[j], 0) + base);
                base *= 10;
            }
        }

        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> o2.getValue() - o1.getValue());

        int n = 9;
        for(Map.Entry<Character, Integer> entry : list) {
            answer += entry.getValue() * n;
            n--;
        }

        System.out.println(answer);
    }
}
