import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        char[] s = br.readLine().toCharArray();
        boolean isPossible;
        int answer = 0;
        int count = 0;

        for(int i = 0; i < m-2; i++){
            if (s[i] == 'I' && s[i+1] == 'O' && s[i+2] == 'I') {
                count ++;

                if(count == n){
                    answer++;
                    count--;
                }
                i++;
            }else{
                count = 0;
            }
        }
        System.out.println(answer);
    }
}