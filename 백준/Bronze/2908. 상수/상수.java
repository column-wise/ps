import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String A = st.nextToken();
        String B = st.nextToken();

        int reversedA = 0;
        int reversedB = 0;

        for(int i = 0; i < A.length(); i++){
            reversedA += (int) ((A.charAt(i) - '0') * Math.pow(10,i));
        }

        for(int i = 0; i < B.length(); i++){
            reversedB += (int) ((B.charAt(i) - '0') * Math.pow(10,i));
        }

        System.out.println(Math.max(reversedA, reversedB));
    }
}