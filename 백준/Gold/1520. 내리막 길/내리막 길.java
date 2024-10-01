import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static int[][] map;
    static boolean visited[][];
    static int dp[][];
    static int R;
    static int C;
    static int count;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        visited = new boolean[R][C];
        dp = new int[R][C];
        for(int i = 0; i < R; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < C; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        count = 0;

        dfs(0,0);

//        for(int i = 0; i < R; i++){
//            for(int j = 0; j < C; j++){
//                System.out.print(dp[i][j]+" ");
//            }
//            System.out.println();
//        }

        System.out.println(dp[0][0]);

    }

    static int dfs(int curX, int curY){

        // 현재 위치를 이미 방문 & 현재 위치에서 도착지까지 갈 수 있는 경로가 있음
        if(visited[curX][curY] && dp[curX][curY]>0){
            return dp[curX][curY];
        }

        // 현재 위치 이미 방문 & 도착지까지의 경로가 없음 -> 더 가볼 필요 없음
        if(visited[curX][curY]) return 0;

        // 목적지 도착
        if(curX == R-1 && curY == C-1){
            dp[curX][curY] = 1;
            return 1;
        }

        visited[curX][curY] = true;

        for(int i = 0; i < 4; i++){
            int newX = curX + dx[i];
            int newY = curY + dy[i];

            if(newX < 0 || newX >= R || newY < 0 || newY >= C) continue;
            if(map[curX][curY] <= map[newX][newY]) continue;

            dp[curX][curY] += dfs(newX,newY);
        }

        return dp[curX][curY];
    }
}