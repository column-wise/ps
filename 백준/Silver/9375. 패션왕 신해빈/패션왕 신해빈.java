import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++){

            Map<String, Integer> categoryCount = new HashMap<>();
            int N = Integer.parseInt(br.readLine());
            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                String wear = st.nextToken();
                String category = st.nextToken();

                if(!categoryCount.containsKey(category)){
                    categoryCount.put(category, 1);
                }

                categoryCount.put(category, categoryCount.get(category)+1);
            }

            int combination = 1;
            for(int count : categoryCount.values()){
                combination *= count;
            }


            combination --;


            System.out.println(combination);
        }
    }
}