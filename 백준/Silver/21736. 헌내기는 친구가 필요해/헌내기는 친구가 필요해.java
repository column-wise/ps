import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0,1,0,-1};
    static int[] dy = {-1,0,1,0};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] campus = new char[N][M];
        boolean[][] visited = new boolean[N][M];
        Deque<Coord> queue = new ArrayDeque<>();
        int x = -1;
        int y = -1;

        for(int i = 0; i < N; i++){
            char[] inputs = br.readLine().toCharArray();
            for(int j = 0; j < M; j++){
                campus[i][j] = inputs[j];
                if(campus[i][j] == 'I'){
                    x = i;
                    y = j;
                }
            }
        }

        queue.add(new Coord(x, y));
        visited[x][y] = true;
        int count = 0;

        while(!queue.isEmpty()){
            Coord cur = queue.poll();
            if(campus[cur.x][cur.y] == 'P'){
                count++;
            }

            for(int i = 0; i < 4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if(visited[nx][ny]) continue;
                if(campus[nx][ny] == 'X') continue;

                queue.add(new Coord(nx, ny));
                visited[nx][ny] = true;
            }
        }

        if(count == 0){
            System.out.println("TT");
        } else{
            System.out.println(count);
        }

    }

    private static class Coord{
        int x;
        int y;
        private Coord(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}