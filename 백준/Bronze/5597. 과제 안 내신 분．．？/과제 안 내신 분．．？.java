import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        int[] attendence = new int[31];
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int index;
        for(int i = 0; i<28; i++){
            index = Integer.parseInt(reader.readLine());
            attendence[index] = 1;
        }

        for(int i = 1; i<31; i++){
            if(attendence[i] == 0){
                System.out.println(i);
            }
        }
        reader.close();
    }
}
