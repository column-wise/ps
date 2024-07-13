import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input[] = reader.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt((input[1]));

        int[] baskets = new int[n];
        for(int i = 0; i<n; i++){
            baskets[i] = i+1;
        }

        for(int i=0; i<m; i++){
            input = reader.readLine().split(" ");
            int start = Integer.parseInt(input[0])-1;
            int end = Integer.parseInt(input[1])-1;
            while(start < end){
                int temp = baskets[start];
                baskets[start] = baskets[end];
                baskets[end] = temp;
                start+=1;
                end-=1;
            }
        }
        for(int i = 0; i<n; i++){
            System.out.print(baskets[i]);
            if(i == n-1) {
                System.out.println();
            }else {
                System.out.print(" ");
            }
        }
        reader.close();
    }
}
