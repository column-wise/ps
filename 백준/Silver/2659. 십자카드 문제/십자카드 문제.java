import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String s = "";
        for(int i = 0; i < 4; i++) {
            s += st.nextToken();
        }

        Map<String, Integer> dict = new HashMap<>();
        int count = 1;

        for(int i = 1111; i <= 9999; i++) {
            String n = Integer.toString(i);
            if(n.contains("0")) continue;

            String clock = getClock(n);
            if(!dict.containsKey(clock)) {
                dict.put(clock, count++);
            }
        }

        System.out.println(dict.get(getClock(s)));
    }

    private static String getClock(String number) {
        String[] clocks = new String[4];
        for(int i = 0; i < 4; i++) {
            clocks[i] = number.substring(i, 4);
            clocks[i] += number.substring(0, i);
        }

        Arrays.sort(clocks);
        return clocks[0];
    }
}