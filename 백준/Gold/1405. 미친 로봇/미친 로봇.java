import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static double totalChance;
    // 동, 서, 남, 북
    static double[] moveChances;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int moves = Integer.parseInt(st.nextToken());
        visited = new boolean[moves*2 + 1][moves*2 + 1];

        totalChance = 0.0;
        moveChances = new double[4];
        for(int i = 0; i < 4; i++) {
            moveChances[i] = Integer.parseInt(st.nextToken()) / 100.0;
        }

        visited[moves][moves] = true;
        dfs(moves, moves, 0, moves, 1);

        System.out.println(totalChance);
    }

    private static void dfs(int x, int y, int curMoves, int targetMoves, double curChance) {
        if(curMoves == targetMoves) {
            totalChance += curChance;
            return;
        }

        for(int i = 0; i < 4; i++) {
            if(moveChances[i] == 0.0) continue;
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(visited[nx][ny]) continue;
            visited[nx][ny] = true;
            dfs(nx, ny, curMoves+1, targetMoves, curChance * moveChances[i]);
            visited[nx][ny] = false;
        }
    }
}