import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String DNAstring = br.readLine();
        int[] limit = new int[4];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 4; i++) {
            limit[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = m-1;

        int[] window = new int[4];
        for(int i = 0; i <= end; i++){
            char c = DNAstring.charAt(i);
            switch (c){
                case 'A':window[0]++;
                    break;
                case 'C':window[1]++;
                    break;
                case 'G':window[2]++;
                    break;
                case 'T':window[3]++;
                    break;
            }
        }
        int ans = 0;
        while(end < n){
            int count = 0;
            for(int j = 0; j < 4; j++){
                if(limit[j] <= window[j]){
                    count++;
                }
            }
            if(count == 4){
                ans ++;
            }

            char c = DNAstring.charAt(start);
            switch (c){
                case 'A':window[0]--;
                    break;
                case 'C':window[1]--;
                    break;
                case 'G':window[2]--;
                    break;
                case 'T':window[3]--;
                    break;
            }

            start ++;
            end ++;
            if(end == n) {
                break;
            }

            c = DNAstring.charAt(end);
            switch (c){
                case 'A':window[0]++;
                    break;
                case 'C':window[1]++;
                    break;
                case 'G':window[2]++;
                    break;
                case 'T':window[3]++;
                    break;
            }

        }
        System.out.println(ans);
    }
}
