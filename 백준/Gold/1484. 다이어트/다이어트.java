import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int G = Integer.parseInt(br.readLine());

        TreeSet<Long> bbst = new TreeSet<>();
        for(long i = 1; i <= 50000; i++) {
            bbst.add(i*i);
        }

        int count = 0;

        for(long i = 1; i <= 50000; i++) {
            if(bbst.contains(i*i+G)) {
                count++;
                System.out.println((long)Math.sqrt(i*i+G));
            }
        }

        if(count == 0) {
            System.out.println(-1);
        }
    }
}