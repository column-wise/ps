import java.util.*;

class Solution {

    public static int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        int[][] cache = new int[n+1][m+1];
        boolean[][] visited = new boolean[n+1][m+1];
        cache[1][1] = 1;

        for(int[] puddle : puddles) {
            visited[puddle[1]][puddle[0]] = true;
        }

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                if(visited[i][j]) continue;
                cache[i][j] += (cache[i-1][j] + cache[i][j-1]) % 1000000007;
            }
        }

        return cache[n][m];
    }

    public static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}