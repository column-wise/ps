import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        char[] chars = s.toCharArray();

        int aCount = 0;
        for(int i = 0; i < chars.length; i++) {
            if(chars[i] == 'a') aCount++;
        }

        int minSwitch = aCount;
        for(int start = 0; start < chars.length; start++) {
            int bCount = 0;
            for(int cursor = 0; cursor < aCount; cursor++) {
                int index = (start + cursor) % chars.length;
                if(chars[index] == 'b') bCount++;
            }

            minSwitch = Math.min(minSwitch, bCount);
        }

        System.out.println(minSwitch);
    }
}
