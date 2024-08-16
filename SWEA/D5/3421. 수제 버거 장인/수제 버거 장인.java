import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static int n;
    static List<List<Integer>> constraints;
    static boolean[] isSelected;
    static int result;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int test_case = 1; test_case <= T; test_case++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            constraints = new ArrayList<>();
            for(int i = 0; i < n; i++){
                constraints.add(new ArrayList<Integer>());
            }
            for(int i = 0; i < m; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken())-1;
                int b = Integer.parseInt(st.nextToken())-1;
                constraints.get(a).add(b);
                constraints.get(b).add(a);
            }
            result = 0;
            for(int i = 0; i <= n; i++){
                isSelected = new boolean[n];
                combination(0, 0, i, new ArrayList<Integer>());
            }

            System.out.println("#"+test_case+" " +result);
        }
    }

    private static void combination(int start, int cnt, int target, List<Integer> chosen){
        if(cnt == target){
            result ++;
        }else{
            for(int i = start; i < n; i++){
                boolean valid = true;
                for(Integer choosed : chosen){
                    if(constraints.get(choosed).contains(i)){
                        valid = false;
                        break;
                    }
                }
                if(valid && !isSelected[i]){
                    isSelected[i] = true;
                    chosen.add(i);
                    combination(i+1, cnt+1, target, chosen);
                    isSelected[i] = false;
                    chosen.remove(chosen.size()-1);
                }
            }
        }
    }
}