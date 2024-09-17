import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        int[][] result = new int[N][M];
        int startX = -1;
        int startY = -1;

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                result[i][j] = -1;
                if(map[i][j] == 2){
                    startX = i;
                    startY = j;
                }
                if(map[i][j] == 0){
                    result[i][j] = 0;
                }
            }
        }

        Deque<Node> queue = new ArrayDeque<>();
        queue.add(new Node(startX, startY, 0));

        boolean[][] visited = new boolean[N][M];
        visited[startX][startY] = true;

        while(!queue.isEmpty()){
            Node cur = queue.poll();
            result[cur.x][cur.y] = cur.depth;

            for(int i = 0; i < 4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if(visited[nx][ny]) continue;
                if(map[nx][ny] == 0) continue;

                visited[nx][ny] = true;
                queue.add(new Node(nx,ny,cur.depth+1));
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                sb.append(result[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static class Node{
        int x;
        int y;
        int depth;

        private Node(int x, int y, int depth){
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }
}