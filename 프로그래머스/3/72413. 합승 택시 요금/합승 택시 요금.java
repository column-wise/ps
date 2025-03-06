import java.util.*;

public class Solution {
    static int[][] graph;
    static int[][] distance;
    static final int INF = 200_000_000;

    public static int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = INF;

        graph = new int[n][n];
        distance = new int[n][n];

        // 0 base index
        s = s - 1;
        a = a - 1;
        b = b - 1;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i == j) continue;
                graph[i][j] = INF;
                distance[i][j] = INF;
            }
        }

        for(int[] fare : fares) {
            int from = fare[0] - 1;
            int to = fare[1] - 1;
            int cost = fare[2];

            graph[from][to] = cost;
            graph[to][from] = cost;
            distance[from][to] = cost;
            distance[to][from] = cost;
        }

        floydWarshall();

        for(int together = 0; together < n; together++) {
            int sum = 0;
            sum += distance[s][together];
            sum += distance[together][a];
            sum += distance[together][b];
            answer = Math.min(answer, sum);
        }

        return answer;
    }

    private static void floydWarshall() {
        for(int k = 0; k < graph.length; k++) {
            for(int i = 0; i < graph.length; i++) {
                for(int j = 0; j < graph.length; j++) {
                    if(i == j) continue;
                    if(distance[i][k] != INF && distance[k][j] != INF) {
                        distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                    }
                }
            }
        }
    }
}
