import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String[] inputs;
    static int[][][] dpTable;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        inputs = br.readLine().split(" ");
        dpTable = new int[5][5][inputs.length];

        step(0,0,0);
        System.out.println(dpTable[0][0][0]);
    }

    static int step(int left, int right, int depth){
        if(depth == inputs.length - 1){
            return 0;
        }

        if(dpTable[left][right][depth] != 0){
            return dpTable[left][right][depth];
        }

        int dest = Integer.parseInt(inputs[depth]);

        dpTable[left][right][depth] = Math.min(step(dest, right, depth+1) + calculateCost(left, dest), step(left, dest, depth+1) + calculateCost(right, dest));

        return dpTable[left][right][depth];
    }

    static int calculateCost(int cur, int next){
        int cost;
        if(cur == next){
            cost = 1;
        }
        else if(cur == 0){
            cost = 2;
        } else if(cur%2 != next%2){
            cost = 3;
        } else{
            cost = 4;
        }
        return cost;
    }

}