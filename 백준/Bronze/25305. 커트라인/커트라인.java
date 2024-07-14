import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = reader.readLine().split(" ");
        int N = Integer.parseInt(inputs[0]);
        int k = Integer.parseInt(inputs[1]);

        Integer[] scores = new Integer[N];
        inputs = reader.readLine().split(" ");

        for(int i = 0; i < N; i++){
            scores[i] = Integer.parseInt(inputs[i]);
        }
        Arrays.sort(scores, Collections.reverseOrder());
        System.out.println(scores[k-1]);
        reader.close();
    }
}
