import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(reader.readLine());

        for(int i = 1; i <= t; i++){
            String input = reader.readLine();
            String[] parts = input.split(" ");
            int a = Integer.parseInt(parts[0]);
            int b= Integer.parseInt(parts[1]);

            int sum = a+b;
            System.out.printf("Case #%d: "+"%d + %d = %d\n",i, a, b, sum);
        }

        reader.close();
    }
}
