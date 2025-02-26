import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static boolean[][] graph;
    static int[][] distance;
    private static int INF = 100_000_000;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new boolean[N][N];
        distance = new int[N][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;

            graph[from][to] = true;
            graph[to][from] = true;
        }

        floydWarshall();

        int min = INF;
        int minNode1 = -1;
        int minNode2 = -1;

        for(int i = 0; i < N; i++) {
            for(int j=i+1; j < N; j++) {
                int sum = 0;
                for(int k = 0; k < N; k++) {
                    if(i==k || j==k) continue;
                    sum += Math.min(distance[i][k], distance[j][k]);
                }
                if(sum < min) {
                    min = sum;
                    minNode1 = i;
                    minNode2 = j;
                }
            }
        }

        System.out.println((minNode1+1) + " " + (minNode2+1) + " " + min * 2);
    }

    private static void floydWarshall() {

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(i == j) continue;
                if(graph[i][j]) distance[i][j] = 1;
                else distance[i][j] = INF;
            }
        }

        for(int k = 0; k < N; k++) {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(i == j || j == k || k == i) continue;
                    if(distance[i][k] + distance[k][j] < distance[i][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                        distance[j][i] = distance[i][k] + distance[k][j];
                    }
                }
            }
        }
    }
}