import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        while(N != 0){

            for(int i = 1; i <= N; i++){
                for(int j = 0; j < i; j++){
                    sb.append("*");
                }
                sb.append("\n");
            }
            N = Integer.parseInt(br.readLine());
        }
        System.out.println(sb);
    }
}