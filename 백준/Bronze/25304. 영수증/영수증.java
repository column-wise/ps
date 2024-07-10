import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] argv) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int x = Integer.parseInt(reader.readLine());
        int n = Integer.parseInt(reader.readLine());

        int sum = 0;

        for(int i = 0; i<n; i++){
            String input = reader.readLine();
            String[] parts = input.split(" ");

            int a = Integer.parseInt(parts[0]);
            int b = Integer.parseInt(parts[1]);

            sum += a * b;
        }

        if(sum == x){
            System.out.println("Yes");
        }else{
            System.out.println("No");
        }

    }
}
