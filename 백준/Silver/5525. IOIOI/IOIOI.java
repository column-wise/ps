import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        String[] s = br.readLine().split("");
        boolean[] valid = new boolean[m];
        boolean isIOI;
        int answer = 0;
        for(int i = 0; i < s.length; i++){
            try{
                if(s[i].equals("I")) {
                    valid[i] = true;
                    valid[i - 1] = false;
                    isIOI = true;
                    for (int j = 1; j <= n; j++) {
                        if(!valid[i-(2*j)]){
                            isIOI = false;
                            break;
                        }
                    }
                    if(isIOI){
                        answer++;
                    }
                }else{
                    valid[i] = false;
                }
            }catch (Exception e){
                continue;
            }
        }
        System.out.println(answer);
    }
}