import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = new String[5];
        for(int i = 0; i < 5; i++){
            inputs[i] = reader.readLine();
        }

        String ans = "";

        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 5; j++){
                if(inputs[j].length() > i){
                    ans += inputs[j].charAt(i);
                }
            }
        }
        System.out.println(ans);
        reader.close();
    }
}
