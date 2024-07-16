import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        int F = Integer.parseInt(reader.readLine());

        N = N/100 * 100;
        while(N%F != 0){
            N += 1;
        }

        String ans = N%100 < 10 ? "0" + Integer.toString(N%100) : Integer.toString(N%100);
        System.out.println(ans);
        reader.close();
    }
}
