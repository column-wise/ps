import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        char[] charArr = input.toCharArray();

        int start = 0;
        int end = input.length()-1;
        boolean flag = true;

        while(start<end){
            if(charArr[start] != charArr[end]){
                flag = false;
                break;
            }
            start += 1;
            end -= 1;
        }
        if(flag){
            System.out.println(1);
        }else{
            System.out.println(0);
        }
        reader.close();
    }
}
