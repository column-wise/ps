import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int[][][] dpTable;
    static int W;
    static int H;
    static int K;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static int[] horseDx = {-1,1,2,2,1,-1,-2,-2};
    static int[] horseDy = {2,2,1,-1,-2,-2,-1,1};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        K = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        dpTable = new int[H][W][K+1];

        for(int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < H; i++) {
            for(int j = 0; j < W; j++) {
                for(int k = 0; k < K+1; k++) {
                    dpTable[i][j][k] = 40000;
                }
            }
        }

        BFS(0,0);

        int min = Integer.MAX_VALUE;
        for(int k = 0; k <= K; k++){
            if(dpTable[H-1][W-1][k] < min){
                min = dpTable[H-1][W-1][k];
            }
        }
        System.out.println(min!=40000?min:-1);
    }

    private static void BFS(int x, int y){
        Deque<Point> queue = new ArrayDeque<>();
        queue.add(new Point(x, y, 0));
        dpTable[0][0][0] = 0;
        while(!queue.isEmpty()){
            Point cur = queue.poll();

            if (cur.x == H-1 && cur.y == W-1){
                break;
            }

            for(int i = 0; i < 4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx<0 || ny<0 || nx>=H || ny>=W) continue;
                if(map[nx][ny] == 1) continue;
                if(dpTable[nx][ny][cur.k] == 40000){
                    dpTable[nx][ny][cur.k] = dpTable[cur.x][cur.y][cur.k] + 1;
                    queue.add(new Point(nx, ny, cur.k));
                }
            }

            if(cur.k<K){
                for(int i = 0; i < 8; i++){
                    int nx = cur.x + horseDx[i];
                    int ny = cur.y + horseDy[i];

                    if(nx<0 || ny<0 || nx>=H || ny>=W) continue;
                    if(map[nx][ny] == 1) continue;
                    if(dpTable[nx][ny][cur.k+1] == 40000){
                        dpTable[nx][ny][cur.k+1] = dpTable[cur.x][cur.y][cur.k] + 1;
                        queue.add(new Point(nx, ny, cur.k+1));
                    }
                }
            }

        }
    }

    private static class Point{
        int x;
        int y;
        int k;

        private Point(int x, int y, int k){
            this.x = x;
            this.y = y;
            this.k = k;
        }
    }
}