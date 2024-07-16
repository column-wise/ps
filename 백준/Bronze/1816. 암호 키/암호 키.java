import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        int size = 1000000;
        ArrayList<Integer> primes = new ArrayList<>();
        boolean[] prime_check = new boolean[size];
        Arrays.fill(prime_check, true);
        prime_check[0] = false;
        prime_check[1] = false;

        for(int i = 2; i < size; i++){
            if(prime_check[i]){
                primes.add(i);
                for(int j = i; j < size; j+=i){
                    prime_check[j] = false;
                }
            }
        }


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(reader.readLine());
        for(int test_case = 0; test_case < T; test_case++){
            boolean isAppropriate = true;
            long N = Long.parseLong(reader.readLine());
            for(int prime:primes){
                if(N%prime == 0){
                    isAppropriate = false;
                    break;
                }
            }
            if(isAppropriate){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
        }

        reader.close();

    }
}
