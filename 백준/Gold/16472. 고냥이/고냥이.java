import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] input = br.readLine().toCharArray();
        int length = input.length;

        int start = 0;
        int end = 0;
        int maxLength = 0;
        Map<Character, Integer> counter = new HashMap<>();
        while(end < length) {
            int count = counter.getOrDefault(input[end], 0);
            counter.put(input[end], count + 1);

            while(counter.size() > N) {
                count = counter.get(input[start]);
                if(count == 1) {
                    counter.remove(input[start]);
                } else {
                    counter.put(input[start], count - 1);
                }
                start++;
            }

            maxLength = Math.max(maxLength, end-start+1);
            end++;
        }

        System.out.println(maxLength);
    }
}
