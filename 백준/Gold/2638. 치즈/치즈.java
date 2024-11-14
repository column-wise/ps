import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int cheeseCount = 0;
        boolean[][] board = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                if(Integer.parseInt(st.nextToken()) == 1) {
                    board[i][j] = false;
                    cheeseCount++;
                } else {
                    board[i][j] = true;
                }
            }
        }

        int time = 0;
        while(cheeseCount > 0) {
            time ++;
            Deque<Point> queue = new ArrayDeque<>();
            queue.add(new Point(0, 0));
            int[][] neighborCount = new int[N][M];
            boolean[][] visited = new boolean[N][M];
            visited[0][0] = true;

            while(!queue.isEmpty()) {
                Point cur = queue.poll();

                for(int i = 0; i < 4; i++) {
                    int x = cur.x + dx[i];
                    int y = cur.y + dy[i];

                    if(x < 0 || x >= N || y < 0 || y >= M) continue;
                    if(visited[x][y]) continue;

                    if(board[x][y]) {
                        queue.add(new Point(x, y));
                        visited[x][y] = true;
                    } else {
                        neighborCount[x][y] ++;
                    }
                }
            }

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(neighborCount[i][j] >= 2) {
                        board[i][j] = true;
                        cheeseCount--;
                    }
                }
            }
        }
        System.out.println(time);
    }

    private static class Point {
        int x;
        int y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}