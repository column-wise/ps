import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(reader.readLine());
        for(int i = 0; i<t; i++){
            String s = reader.readLine();
            char first = s.charAt(0);
            char last = s.charAt(s.length()-1);

            System.out.println(String.valueOf(first)+String.valueOf(last));

        }
    }
}
