import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        String[] parts = input.split(" ");
        int n = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);

        int [] baskets = new int[n];

        for(int i = 0; i<n; i++){
            baskets[i] = i+1;
        }

        for(int i = 0; i<m; i++){
            input = reader.readLine();
            parts = input.split(" ");
            int a = Integer.parseInt(parts[0]);
            int b = Integer.parseInt(parts[1]);
            int temp = baskets[a-1];
            baskets[a-1] = baskets[b-1];
            baskets[b-1] = temp;
        }

        for(int i = 0; i<n; i++){
            System.out.print(baskets[i]);
            if(i == n-1){
                System.out.print("\n");
            }else{
                System.out.print(" ");
            }
        }

        reader.close();
    }
}