import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str;
        while(!(str = br.readLine()).equals("#")){
            char[] charArray = str.toCharArray();

            int count = 0;

            for(int i = 0; i < charArray.length; i++){
                char character = Character.toLowerCase(charArray[i]);
                if(character == 'a' || character == 'e' || character == 'i' || character == 'o' || character == 'u'){
                    count++;
                }
            }

            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }
}