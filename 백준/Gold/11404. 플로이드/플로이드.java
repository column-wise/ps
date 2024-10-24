import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static final int INF = 100000000;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[][] adjMatrix = new int[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(i==j) continue;
                adjMatrix[i][j] = INF;
            }
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            adjMatrix[from][to] = Math.min(adjMatrix[from][to], cost);
        }

        floydWarshall(adjMatrix);

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                sb.append(adjMatrix[i][j]==INF?0:adjMatrix[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void floydWarshall(int[][] graph){
        for(int k = 0; k < N; k++){
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(graph[i][j] > graph[i][k] + graph[k][j]){
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }
    }
}