import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int minTailCount;
    static boolean[][] copiedCoins;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        boolean[][] coins = new boolean[N][N];

        minTailCount = 0;
        copiedCoins = new boolean[N][N];

        for(int i = 0; i < N; i++){
            String[] inputs = br.readLine().split("");
            for(int j = 0; j < N; j++){
                coins[i][j] = inputs[j].equals("H");
                if(!coins[i][j]) minTailCount++;
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                copiedCoins[j] = coins[j].clone();
            }
            combination(0, 0, i);
        }
        System.out.println(minTailCount);
    }

    private static void combination(int start, int curDepth, int targetDepth){

        if(curDepth == targetDepth){
            int tailSum = 0;

            for(int j = 0; j < N; j++){
                int colTailCount = 0;
                for(int i = 0; i < N; i++){
                    if(!copiedCoins[i][j]) colTailCount++;
                }

                if(colTailCount > N/2){
                    tailSum += N - colTailCount;
                } else{
                    tailSum += colTailCount;
                }
            }

            minTailCount = Math.min(minTailCount, tailSum);
            return;
        }
        for(int i = start; i < N; i++){

            flip(i);
            combination(i+1, curDepth+1, targetDepth);
            flip(i);
        }
    }

    private static void flip(int targetRow){
        for(int i = 0; i < N; i++){
            copiedCoins[targetRow][i] = !copiedCoins[targetRow][i];
        }
    }
}