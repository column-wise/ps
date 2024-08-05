import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int n;
    static int min = Integer.MAX_VALUE;
    static int[][] flavor;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        flavor = new int[n][2];

        for(int i = 0; i < n; i++){
            String[] inputs = br.readLine().split(" ");
            flavor[i][0] = Integer.parseInt(inputs[0]);
            flavor[i][1] = Integer.parseInt(inputs[1]);
        }

        for(int i = 1; i <= n; i++){
            List<Integer> list = new ArrayList<>();
            BT(list, i);
        }

        System.out.println(min);

    }

    public static void BT(List<Integer> list, int size){
        if(list.size() == size){
            int sour = 1;
            int bitter = 0;
            for(int idx:list){
                sour*=flavor[idx][0];
                bitter+=flavor[idx][1];
            }
            min = Math.min(min, Math.abs(sour-bitter));
        } else {
            for(int i = 0; i < n; i++){
                if(!list.contains(i)){
                    list.add(i);
                    BT(list,size);
                    list.remove((Integer)i);
                }
            }
        }
    }

}
