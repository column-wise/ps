import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<String> drawingBook = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        drawingBook.add("null");
        for(int i = 1; i <= n; i++) {
            String name = br.readLine();
            drawingBook.add(name);
            map.put(name,i);
        }

        for(int i = 0; i < m; i++) {
            String input = br.readLine();
            try {
                int intInput = Integer.parseInt(input);
                System.out.println(drawingBook.get(intInput));
            }catch(NumberFormatException e){
                System.out.println(map.get(input));
            }

        }
    }
}
