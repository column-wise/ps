import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int[] direction = {1,-1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] board = new int[100][100];
        int[][] dpTable = new int[100][100];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;

            if((from/10) % 2 == 0){
                board[from/10][from%10] = to;
            } else{
                board[from/10][9 - from%10] = to;
            }
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;

            if((from/10) % 2 == 0){
                board[from/10][from%10] = to;
            } else{
                board[from/10][9 - from%10] = to;
            }
        }

        Deque<Point> queue = new ArrayDeque<>();
        queue.add(new Point(0,0));

        while(!queue.isEmpty()){
            Point cur = queue.poll();

            if(cur.x == 9 && cur.y == 0) break;

            int nextX = cur.x;
            int nextY = cur.y;
            int minTimes = dpTable[cur.x][cur.y];

            for(int i = 1; i <= 6; i++){
                nextY += direction[nextX%2];
                if(nextY < 0 || nextY >= 10){
                    nextY -= direction[nextX%2];
                    nextX ++;
                }

                if(nextX >= 10) continue;

                if(dpTable[nextX][nextY] == 0 && board[nextX][nextY] == 0){
                    dpTable[nextX][nextY] = minTimes + 1;
                    queue.add(new Point(nextX, nextY));
                }
                
                // 사다리 혹은 뱀이 있음
                if(board[nextX][nextY] != 0){
                    int to = board[nextX][nextY];

                    int x = to/10;
                    int y = -1;

                    if((x) % 2 == 0){
                        y = to % 10;
                    } else {
                        y = 9 - (to % 10);
                    }

                    if(dpTable[x][y] == 0){
                        dpTable[x][y] = minTimes + 1;
                        queue.add(new Point(x, y));
                    }
                }
                
            }

        }

        System.out.println(dpTable[9][0]);
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