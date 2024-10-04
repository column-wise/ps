import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    static int[] dx = {0,1,0,-1};
    static int[] dy = {-1,0,1,0};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        int N = Integer.parseInt(inputs[0]);
        int M = Integer.parseInt(inputs[1]);
        int K = Integer.parseInt(inputs[2]);

        int[][] map = new int[N][M];
        int[][][] dp = new int[N][M][K+1];

        for(int i = 0; i < N; i++){
            inputs = br.readLine().split("");
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(inputs[j]);
            }
        }

        Deque<Point> queue = new ArrayDeque<>();
        queue.add(new Point(0,0,0,1));
        dp[0][0][0] = 1;

        while(!queue.isEmpty()){
            Point cur = queue.poll();
            int x = cur.x;
            int y = cur.y;
            int k = cur.k;
            int dist = cur.dist;

            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || nx>= N || ny < 0 || ny >= M) continue;
                if(dp[nx][ny][k] != 0) continue;
                if(map[nx][ny] == 1){
                    for(int j = k; j < K; j++){
                        if(dp[nx][ny][k+1] == 0) {
                            dp[nx][ny][k + 1] = dist + 1;
                            queue.add(new Point(nx, ny, k + 1, dist + 1));
                        }
                    }
                    continue;
                }

                queue.add(new Point(nx, ny, k, dist + 1));
                dp[nx][ny][k] = dist + 1;
            }

        }

        int min = N*M;
        for(int k = 0; k < K+1; k++){
            if(dp[N-1][M-1][k] != 0){
                min = Math.min(min, dp[N-1][M-1][k]);
            }
        }

        if(N==1 && M==1){
            System.out.println(min);
        } else{
            System.out.println(min==N*M?-1:min);
        }
    }
    private static class Point{
        int x;
        int y;
        int k;
        int dist;

        private Point(int x, int y, int k, int dist){
            this.x = x;
            this.y = y;
            this.k = k;
            this.dist = dist;
        }
    }
}