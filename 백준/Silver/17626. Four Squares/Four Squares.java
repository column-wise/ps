import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int N;
    static List<Integer> squareNumbers;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        squareNumbers = new ArrayList<>();
        int num = 1;
        while(true){
            if(num*num > N) break;
            squareNumbers.add(num*num);
            num++;
        }

        int ans = 4;

        for(int i = 1; i < 4; i++){
            if(N == find(0, i, squareNumbers.size()-1, 0)){
                ans = i;
                break;
            }
        }
        System.out.println(ans);
    }

    private static int find(int depth, int target, int idx, int sum){
        if(depth == target){
            return sum;
        } else{
            for(int i = idx; i >= 0; i--){

                int ret = find(depth+1, target, i, sum+squareNumbers.get(i));

                if(ret == N){
                    return ret;
                }
            }
        }
        return 50001;
    }
}