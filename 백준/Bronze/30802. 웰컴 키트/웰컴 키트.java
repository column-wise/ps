import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        String[] shirts = reader.readLine().split(" ");
        String[] bundles = reader.readLine().split(" ");
        int shirt_bundle = Integer.parseInt(bundles[0]);
        int pen_bundle = Integer.parseInt(bundles[1]);

        int T = 0;

        for(String size:shirts){
            int s = Integer.parseInt(size);
            T += s/shirt_bundle;
            if(s%shirt_bundle != 0) {
                T += 1;
            }
        }

        int P = n/pen_bundle;
        int R = n%pen_bundle;

        System.out.println(T);
        System.out.print(P);
        System.out.print(" ");
        System.out.println(R);
        reader.close();
    }
}