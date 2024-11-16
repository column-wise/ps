import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[] dy = {0,-1,0,1};
    static int[] dx = {1,0,-1,0};
    static int[][] board;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        board = new int[101][101];

        for(int c = 0; c < N; c++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            drawDragonCurve(x, y, d, g);
        }

        int squares = 0;
        for(int i = 0; i < 100; i++) {
            for(int j = 0; j < 100; j++) {
                if(board[i][j] > 0) {
                    if(board[i+1][j] > 0 && board[i][j+1] > 0 && board[i+1][j+1] > 0) {
                        squares ++;
                    }
                }
            }
        }
        System.out.println(squares);
    }

    private static void drawDragonCurve(int x, int y, int d, int g) {
        List<Integer> directions = new ArrayList<>();
        directions.add(d);
        int curX = x;
        int curY = y;
        int curGen = 0;
        board[curY][curX] += 1;

        while(curGen < g) {
            int len = directions.size();
            for(int i = len - 1; i >= 0; i--) {
                directions.add((directions.get(i) + 1) % 4);
            }
            curGen++;
        }

        for(int curDir : directions) {
            curX += dx[curDir];
            curY += dy[curDir];
            board[curY][curX] += 1;
        }
    }
}