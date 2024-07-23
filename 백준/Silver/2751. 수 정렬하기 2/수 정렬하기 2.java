import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        List<Integer> l = new ArrayList<>();
        for(int i = 0; i < n; i++){
            l.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(l);
        for(int i = 0; i < n; i ++){
            sb.append(l.get(i));
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
