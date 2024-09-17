import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0,1,0,-1,0,0};
    static int[] dy = {1,0,-1,0,0,0};
    static int[] dz = {0,0,0,0,1,-1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[][][] box = new int[H][N][M];
        boolean visited[][][] = new boolean[H][N][M];
        Deque<Tomato> queue = new ArrayDeque<>();

        for(int h = 0; h < H; h++){
            for(int n = 0; n < N; n++){
                st = new StringTokenizer(br.readLine());
                for(int m = 0; m < M; m++){
                    box[h][n][m] = Integer.parseInt(st.nextToken());

                    if(box[h][n][m] == 1){
                        visited[h][n][m] = true;
                        queue.add(new Tomato(h, n, m, 0));
                    }

                }
            }
        }

        int maxOrder = 0;

        while(!queue.isEmpty()){
            Tomato cur = queue.poll();
            maxOrder = Math.max(maxOrder, cur.order);

            for(int i = 0; i < 6; i++){
                int nx = cur.r + dx[i];
                int ny = cur.c + dy[i];
                int nz = cur.h + dz[i];

                if(nx < 0 || N <= nx || ny < 0 || M <= ny || nz < 0 || H <= nz) continue;
                if(visited[nz][nx][ny]) continue;
                if(box[nz][nx][ny] == -1) continue;

                visited[nz][nx][ny] = true;
                box[nz][nx][ny] = 1;
                queue.add(new Tomato(nz, nx, ny, cur.order+1));
            }

        }

        boolean isAllTomatoRipe = true;
        for(int h = 0; h < H; h++){
            for(int n = 0; n < N; n++){
                for(int m = 0; m < M; m++){
                    if(box[h][n][m] == 0){
                        isAllTomatoRipe = false;
                        break;
                    }
                }
                if(!isAllTomatoRipe){
                    break;
                }
            }
            if(!isAllTomatoRipe){
                break;
            }
        }

        if(isAllTomatoRipe){
            System.out.println(maxOrder);
        } else{
            System.out.println(-1);
        }


    }

    static class Tomato{
        int r;
        int c;
        int h;
        int order;

        private Tomato(int h, int r, int c, int order){
            this.r = r;
            this.c = c;
            this.h = h;
            this.order = order;
        }
    }
}