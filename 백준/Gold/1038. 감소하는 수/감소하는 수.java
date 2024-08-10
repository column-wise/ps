import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<Long> list;
    static BufferedWriter writer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        for(int i = 1; i<=10; i++){
            backTracking(i, 0, 0, 10);
        }
        try{
            System.out.println(list.get(n));
        }catch (IndexOutOfBoundsException e){
            System.out.println(-1);
        }

    }
    public static void backTracking(int length, int count, long n, int l){
        if (length == count) {
            list.add(n);
        } else {
            for (int i = 0; i < l; i++) {
                backTracking(length, count + 1, n * 10 + i, i);
            }
        }
    }

}
