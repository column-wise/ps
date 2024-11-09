import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static final long INF = 100001L *100001;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int sizeA = Integer.parseInt(st.nextToken());
        int sizeB = Integer.parseInt(st.nextToken());
        int targetA = Integer.parseInt(st.nextToken());
        int targetB = Integer.parseInt(st.nextToken());

        long[][] dp = new long[sizeA + 1][sizeB + 1];
        for(int i = 0; i <= sizeA; i++) {
            for(int j = 0; j <= sizeB; j++) {
                dp[i][j] = INF;
            }
        }

        Deque<Status> queue = new ArrayDeque<>();
        queue.add(new Status(0,0,0));
        dp[0][0] = 0;

        while(!queue.isEmpty()) {
            Status status = queue.poll();
            int x = status.x;
            int y = status.y;

            int nx = sizeA;
            int ny = y;
            if(dp[nx][ny] > status.turn + 1) {
                dp[nx][ny] = status.turn + 1;
                queue.add(new Status(nx, ny, status.turn + 1));
            }

            nx = x;
            ny = sizeB;
            if(dp[nx][ny] > status.turn + 1) {
                dp[nx][ny] = status.turn + 1;
                queue.add(new Status(nx, ny, status.turn + 1));
            }

            nx = x;
            ny = 0;
            if(dp[nx][ny] > status.turn + 1) {
                dp[nx][ny] = status.turn + 1;
                queue.add(new Status(nx, ny, status.turn + 1));
            }

            nx = 0;
            ny = y;
            if(dp[nx][ny] > status.turn + 1) {
                dp[nx][ny] = status.turn + 1;
                queue.add(new Status(nx, ny, status.turn + 1));
            }

            int temp = x+y;
            if(temp > sizeA) {
                nx = sizeA;
                ny = temp - sizeA;
                if(dp[nx][ny] > status.turn + 1) {
                    dp[nx][ny] = status.turn + 1;
                    queue.add(new Status(nx, ny, status.turn + 1));
                }
            } else {
                nx = temp;
                ny = 0;
                if(dp[nx][ny] > status.turn + 1) {
                    dp[nx][ny] = status.turn + 1;
                    queue.add(new Status(nx, ny, status.turn + 1));
                }
            }

            if(temp > sizeB) {
                nx = temp - sizeB;
                ny = sizeB;
                if(dp[nx][ny] > status.turn + 1) {
                    dp[nx][ny] = status.turn + 1;
                    queue.add(new Status(nx, ny, status.turn + 1));
                }
            } else {
                nx = 0;
                ny = temp;
                if(dp[nx][ny] > status.turn + 1) {
                    dp[nx][ny] = status.turn + 1;
                    queue.add(new Status(nx, ny, status.turn + 1));
                }
            }
        }

        System.out.println(dp[targetA][targetB] != INF ? dp[targetA][targetB] : -1);
    }

    static class Status {
        int x;
        int y;
        int turn;

        Status(int x, int y, int turn) {
            this.x = x;
            this.y = y;
            this.turn = turn;
        }
    }
}