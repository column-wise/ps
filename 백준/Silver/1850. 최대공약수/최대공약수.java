import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter((new OutputStreamWriter(System.out)));
        String[] inputs = br.readLine().split(" ");
        long d = Long.parseLong(inputs[0]);
        long r = Long.parseLong(inputs[1]);
        long temp;

        if(d<r){
            temp = r;
            r = d;
            d = temp;
        }

        while(d%r != 0) {
            temp = r;
            r = d % r;
            d = temp;
        }

        for(int i = 0; i < r; i++){
            bw.write('1');
        }
        br.close();
        bw.close();
    }
}
