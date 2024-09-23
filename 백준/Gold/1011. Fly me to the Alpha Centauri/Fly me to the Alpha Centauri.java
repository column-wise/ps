import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            int distance = to - from;

            int section = 1;
            boolean isSectionUpdateNeeded = false;
            int times = 0;

            while(distance > 0) {
                distance -= section;
                times++;
                if(isSectionUpdateNeeded){
                    section++;
                    isSectionUpdateNeeded = false;
                } else{
                    isSectionUpdateNeeded = true;
                }
            }

            System.out.println(times);
        }
    }
}