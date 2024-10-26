import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = new String[3];
        int i = 0;
        int idx = 0;

        for(int j = 0; j < 3; j++){
            inputs[j] = br.readLine();

            try{
                i = Integer.parseInt(inputs[j]);
                idx = j;
            } catch(Exception e){
                continue;
            }

        }

        int target = i + (3-idx);

        if(target % 3 == 0 && target % 5 == 0){
            System.out.println("FizzBuzz");
        } else if(target % 3 == 0){
            System.out.println("Fizz");
        } else if(target % 5 == 0){
            System.out.println("Buzz");
        } else{
            System.out.println(target);
        }

    }
}