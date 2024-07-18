import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(reader.readLine());
        for(int test_case = 1; test_case <= T; test_case++){
            int N = Integer.parseInt(reader.readLine());
            String[] inputs = reader.readLine().split(" ");
            int[] kids = new int[N];
            for(int i = 0; i < N; i++){
                kids[i] = Integer.parseInt(inputs[i]);
            }

            int count = 0;
            int[] plus = new int[N];
            int[] minus = new int[N];

            while(checkDifferent(kids)){
                kids = setEven(kids);
                if(!checkDifferent(kids)){
                    break;
                }
                for(int i = 0; i < N; i++){
                    if(i != N-1){
                        plus[i+1] = kids[i]/2;
                    }else{
                        plus[0] = kids[i]/2;
                    }
                    minus[i] = kids[i]/2;
                }
                for(int i = 0; i < N; i++){
                    kids[i] += plus[i];
                    kids[i] -= minus[i];
                }
                count += 1;
            }

            System.out.println(count);
        }
    }
    public static int[] setEven(int[] kids){
        for(int i = 0; i < kids.length; i++) {
            if (kids[i] % 2 == 1) {
                kids[i] += 1;
            }
        }
        return kids;
    }

    public static boolean checkDifferent(int[] kids){
        boolean flag = false;
        for(int i = 1; i < kids.length; i++){
            if (kids[i] != kids[i-1]){
                flag = true;
            }
        }
        return flag;
    }

}
