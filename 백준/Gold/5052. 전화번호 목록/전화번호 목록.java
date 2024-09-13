import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());
            List<String> list = new ArrayList<>();
            List<Integer> lengths = new ArrayList<>();
            Set<String> set = new HashSet<>();
            for(int i = 0; i < N; i++) {
                String s = br.readLine();
                list.add(s);
                set.add(s);
                if(!lengths.contains(s.length())){
                    lengths.add(s.length());
                }

            }

            boolean isConsistent = true;
            Collections.sort(list);
            Collections.sort(lengths);

            for(int i = 0; i < N; i++){
                for(int j = 0; j < lengths.size(); j++){
                    String s = list.get(i);
                    int l = lengths.get(j);
                    if(s.length() <= l) break;

                    String sub = s.substring(0, l);
                    if(set.contains(sub)){
                        isConsistent = false;
                        break;
                    }
                }
                if(!isConsistent) break;
            }
            if(isConsistent) System.out.println("YES");
            else System.out.println("NO");
        }
    }
}