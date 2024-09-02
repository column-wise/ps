import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int min = Integer.MAX_VALUE;
    static int[][] adjMatrix;
    static boolean[] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        adjMatrix = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                adjMatrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N; i++){
            visited = new boolean[N];
            visited[i] = true;
            dfs(i, i, 0, 0);
        }

        System.out.println(min);

    }

    private static void dfs(int start, int now, int cost, int depth){
        if(depth == N-1 && adjMatrix[now][start] > 0){
            min = Math.min(min, cost + adjMatrix[now][start]);
        }

        for(int i = 0; i < N; i++){
            if(!visited[i]){
                if(adjMatrix[now][i] > 0){
                    visited[i] = true;
                    dfs(start, i, cost + adjMatrix[now][i], depth+1);
                    visited[i] = false;
                }
            }
        }
    }

}