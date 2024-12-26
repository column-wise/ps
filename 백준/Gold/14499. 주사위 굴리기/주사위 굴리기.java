import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        Dice dice = new Dice();

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++) {
            int direction = Integer.parseInt(st.nextToken()) - 1;

            int nx = x + dx[direction];
            int ny = y + dy[direction];

            if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

            dice.move(direction);
            x = nx;
            y = ny;

            if(map[x][y] == 0) {
                map[x][y] = dice.cells[6].value;
            } else {
                dice.cells[6].value = map[x][y];
                map[x][y] = 0;
            }

            System.out.println(dice.cells[1].value);
        }
    }

    private static class Dice {
        Cell[] cells = new Cell[6+1];

        public Dice() {
            for(int i = 1; i <= 6; i++) {
                cells[i] = new Cell();
            }
        }

        public void move(int direction) {
            Cell temp = cells[1];
            if(direction == 0) {
                cells[1] = cells[4];
                cells[4] = cells[6];
                cells[6] = cells[3];
                cells[3] = temp;
            } else if(direction == 1) {
                cells[1] = cells[3];
                cells[3] = cells[6];
                cells[6] = cells[4];
                cells[4] = temp;
            } else if(direction == 2) {
                cells[1] = cells[5];
                cells[5] = cells[6];
                cells[6] = cells[2];
                cells[2] = temp;
            } else if(direction == 3) {
                cells[1] = cells[2];
                cells[2] = cells[6];
                cells[6] = cells[5];
                cells[5] = temp;
            }
        }
    }

    private static class Cell {
        int value;
    }
}