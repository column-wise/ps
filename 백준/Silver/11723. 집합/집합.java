import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        boolean[] set = new boolean[21];

        int M = Integer.parseInt(br.readLine());
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            String operation = st.nextToken();
            int num;

            switch(operation){
                case "add" :
                    num = Integer.parseInt(st.nextToken());
                    set[num] = true;
                    break;
                case "remove":
                    num = Integer.parseInt(st.nextToken());
                    set[num] = false;
                    break;
                case "check":
                    num = Integer.parseInt(st.nextToken());
                    sb.append((set[num] ? 1 : 0)).append("\n");
                    break;
                case "toggle":
                    num = Integer.parseInt(st.nextToken());
                    set[num] = !set[num];
                    break;
                case "all":
                    Arrays.fill(set, true);
                    break;
                case "empty":
                    Arrays.fill(set, false);
                    break;
            }
        }
        System.out.println(sb);
    }
}