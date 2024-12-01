import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        int lowest = 0;
        int highest = 256;

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] < lowest) lowest = map[i][j];
                if(map[i][j] > highest) highest = map[i][j];
            }
        }

        int minSpentTime = 500 * 500 * 256 * 2;
        int maxHeight = 0;

        for(int target = highest; target >= lowest; target--) {
            int spentTime = 0;
            int blocksHaving = B;

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(map[i][j] < target) {
                        spentTime += target - map[i][j];
                        blocksHaving -= target - map[i][j];
                    } else if(map[i][j] > target) {
                        spentTime += (map[i][j] - target) * 2;
                        blocksHaving += map[i][j] - target;
                    }
                }
            }
            
            if(blocksHaving >= 0 && minSpentTime > spentTime) {
                minSpentTime = spentTime;
                maxHeight = target;
            }
        }

        System.out.println(minSpentTime + " " + maxHeight);
    }
}