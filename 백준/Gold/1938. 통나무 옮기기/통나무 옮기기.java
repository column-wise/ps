import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static final int INF = 1000000;
    static int[] dx = {0,1,1,1,0,-1,-1,-1};
    static int[] dy = {1,1,0,-1,-1,-1,0,1};
    static boolean[][] field;
    static int[][][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        List<Point> B = new ArrayList<>();
        List<Point> E = new ArrayList<>();

        field = new boolean[N][N];
        for(int i = 0; i < N; i++) {
            String[] inputs = br.readLine().split("");
            for(int j = 0; j < N; j++) {
                if(inputs[j].equals("B")) {
                    field[i][j] = true;
                    B.add(new Point(i, j));
                } else if(inputs[j].equals("E")) {
                    field[i][j] = true;
                    E.add(new Point(i, j));
                } else if(inputs[j].equals("0")) {
                    field[i][j] = true;
                } else {
                    field[i][j] = false;
                }
            }
        }
        Log start = new Log();
        start.turn = 0;
        start.center = new Point(B.get(1).x, B.get(1).y);
        if(B.get(0).x == B.get(1).x && B.get(1).x == B.get(2).x) {
            start.placeDirection = 0;
        } else {
            start.placeDirection = 1;
        }

        Log end = new Log();
        end.center = new Point(E.get(1).x, E.get(1).y);
        if(E.get(0).x == E.get(1).x && E.get(1).x == E.get(2).x) {
            end.placeDirection = 0;
        } else {
            end.placeDirection = 1;
        }

        dp = new int[N][N][2];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                Arrays.fill(dp[i][j], INF);
            }
        }

        Deque<Log> queue = new ArrayDeque<>();
        queue.offer(start);
        dp[start.center.x][start.center.y][start.placeDirection] = start.turn;

        while(!queue.isEmpty()) {
            Log cur = queue.poll();
            int x = cur.center.x;
            int y = cur.center.y;
            int placeDirection = cur.placeDirection;

            for(int i = 0; i < 4; i++) {
                if(cur.checkCanMove(i*2) && dp[x+dx[i*2]][y+dy[i*2]][placeDirection] > cur.turn+1) {
                    queue.offer(new Log(new Point(x+dx[i*2], y+dy[i*2]), cur.turn+1, placeDirection));
                    dp[x+dx[i*2]][y+dy[i*2]][placeDirection] = cur.turn + 1;
                };
            }

            if(cur.checkCanRotate() && dp[x][y][(placeDirection+1) % 2] > cur.turn+1) {
                queue.offer(new Log(new Point(x, y), cur.turn + 1, (placeDirection+1) % 2));
                dp[x][y][(placeDirection+1) % 2] = cur.turn + 1;
            }
        }
        System.out.println(dp[end.center.x][end.center.y][end.placeDirection] != INF ? dp[end.center.x][end.center.y][end.placeDirection] : 0);
    }

    static class Log {
        Point center;
        int turn;

        // 0 이면 가로 1 이면 세로
        int placeDirection;

        public Log() {
            super();
        }

        public Log(Point center, int turn, int placeDirection) {
            this.center = center;
            this.turn = turn;
            this.placeDirection = placeDirection;
        }

        public boolean checkCanMove(int direction) {
            boolean canMove = true;
            int x = center.x;
            int y = center.y;
            int nx = x;
            int ny = y;

            if((direction/2) % 2 == placeDirection) {
                nx = x + dx[direction] * 2;
                ny = y + dy[direction] * 2;
                if(nx < 0 || nx >= N || ny < 0 || ny >= N || !field[nx][ny]) {
                    canMove = false;
                }
            } else {
                for(int i = direction-1; i <= direction+1; i++) {
                    if(i >= 0) {
                        nx = x + dx[i];
                        ny = y + dy[i];
                    } else {
                        nx = x + dx[i+8];
                        ny = y + dy[i+8];
                    }
                    if(nx < 0 || nx >= N || ny < 0 || ny >= N || !field[nx][ny]) {
                        canMove = false;
                        break;
                    }
                }
            }
            return canMove;
        }

        public boolean checkCanRotate() {
            boolean canMove = true;
            int x = center.x;
            int y = center.y;
            int nx;
            int ny;

            for(int i = 0; i < 8; i++) {
                nx = x + dx[i];
                ny = y + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N || !field[nx][ny]) {
                    canMove = false;
                }
            }
            return canMove;
        }
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}