import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());

        String input = reader.readLine();
        String[] parts = input.split(" ");

        int[] nums = new int[parts.length];
        for(int i = 0; i<parts.length; i++){
            nums[i] = Integer.parseInt(parts[i]);
        }

        int v = Integer.parseInt(reader.readLine());
        int count = 0;

        for(int number : nums){
            if(v == number){
                count ++;
            }
        }

        System.out.println(count);
        reader.close();
    }
}
