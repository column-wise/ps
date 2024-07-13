import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;

        while ((input = reader.readLine()) != null) {
            if(input.isEmpty()){
                break;
            }
            // 입력된 한 줄의 문자열을 출력
            System.out.println(input);
        }
    }
}
