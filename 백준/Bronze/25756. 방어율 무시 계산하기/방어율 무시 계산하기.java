import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        double V = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()){
            double A = Double.parseDouble(st.nextToken());
            V = 1 - ((1-V/100)*(1-A/100));
            V = V*100;
            System.out.println(V);
        }
    }
}