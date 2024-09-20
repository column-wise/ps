import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] adjMatrix;
    static int[][] res;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        adjMatrix = new int[N][N];
        res = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                adjMatrix[i][j] = Integer.parseInt(st.nextToken());
                res[i][j] = adjMatrix[i][j];
            }
        }

        for(int i = 0; i < N; i++){
            boolean[] visited = new boolean[N];
            dfs(i, i, visited);
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                System.out.print(res[i][j] + " ");
            }
            System.out.println();
        }
    }
    private static void dfs(int start, int cur, boolean[] visited){
        for(int i = 0; i < N; i++){
            if(!visited[i] && adjMatrix[cur][i] == 1){
                res[cur][i] = 1;
                res[start][i] = 1;
                visited[i] = true;
                dfs(start, i, visited);
            }
        }
    }
}