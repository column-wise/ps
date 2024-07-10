import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        String [] parts = input.split(" ");

        int n = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        int [] result = new int[n];

        for(int i = 0; i<m; i++){
            input = reader.readLine();
            parts = input.split(" ");
            int start = Integer.parseInt(parts[0])-1;
            int end = Integer.parseInt(parts[1])-1;
            int ball = Integer.parseInt(parts[2]);

            for(int j = start; j<=end; j++){
                result[j] = ball;
            }

        }

        for(int i = 0; i<result.length;i++){
            System.out.print(result[i]);
            if(i<result.length-1){
                System.out.print(" ");
            }
        }

    }
}
