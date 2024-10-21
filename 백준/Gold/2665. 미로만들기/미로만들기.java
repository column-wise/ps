import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean[][] map = new boolean[N][N];
        int[][] dp = new int[N][N];

        for(int i = 0; i < N; i++){
            String[] inputs = br.readLine().split("");
            for(int j = 0; j < N; j++){
                map[i][j] = inputs[j].equals("1");
                dp[i][j] = N*N;
            }
        }

        dp[0][0] = 0;
        Deque<Point> queue = new ArrayDeque<>();
        queue.add(new Point(0,0));
        while(!queue.isEmpty()) {
            Point cur = queue.poll();
            int x = cur.x;
            int y = cur.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (!map[nx][ny] && dp[nx][ny] > dp[x][y] + 1) {
                    dp[nx][ny] = dp[x][y] + 1;
                    queue.add(new Point(nx, ny));
                } else if (map[nx][ny] && dp[nx][ny] > dp[x][y]) {
                    dp[nx][ny] = dp[x][y];
                    queue.add(new Point(nx, ny));
                }
            }
        }

//        for(int i = 0 ; i < N; i++){
//            for(int j = 0; j < N; j++){
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }

        System.out.println(dp[N-1][N-1]);
    }

    private static class Point{
        int x;
        int y;
        private Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}