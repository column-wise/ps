import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++){
            String[] functions = br.readLine().split("");
            int n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(),"[], ");
            List<Integer> arr = new ArrayList<>();
            for(int i = 0; i < n; i++){
                arr.add(Integer.parseInt(st.nextToken()));
            }

            boolean valid = true;
            boolean reversed = false;
            boolean[] removed = new boolean[n];
            int head = 0;
            int tail = n-1;

            for(int i = 0; i < functions.length; i++){
                if(functions[i].equals("R")){
                    reversed = !reversed;
                }else{
                    if(head<=tail){
                        if(reversed){
                            removed[tail] = true;
                            tail--;
                        }else{
                            removed[head] = true;
                            head++;
                        }
                    }else{
                        valid = false;
                        break;
                    }
                }
            }
            if(valid) {
                StringBuilder sb = new StringBuilder();
                sb.append("[");
                if(!reversed) {
                    for (int i = 0; i < n; i++) {
                        if(!removed[i]){
                            sb.append(arr.get(i));
                            if (i != tail) {
                                sb.append(",");
                            }
                        }
                    }
                }else{
                    for (int i = n-1; i >= 0; i--) {
                        if(!removed[i]){
                            sb.append(arr.get(i));
                            if (i != head) {
                                sb.append(",");
                            }
                        }
                    }
                }
                sb.append("]");
                System.out.println(sb.toString());
            }else{
                System.out.println("error");
            }
        }
    }
}