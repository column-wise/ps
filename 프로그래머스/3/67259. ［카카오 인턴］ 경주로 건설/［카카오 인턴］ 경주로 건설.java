import java.lang.Math;
import java.util.*;

class Solution {
    public int solution(int[][] board) {
        int answer = 0;
        int N = board.length;
        int[][][] dp = new int[N][N][4];
        int INF = 200000000;
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                for(int k = 0; k < 4; k++) {
                    dp[i][j][k] = INF;
                }
            }
        }
        
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(0, 0, -1, -500));
        for(int i = 0; i < 4; i++) {
            dp[0][0][i] = 0;
        }
        
        while(!queue.isEmpty()) {
            Node cur = queue.poll();
            int x = cur.x;
            int y = cur.y;
            int direction = cur.direction;
            int cost = cur.cost;
            
            for(int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                
                if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if(board[nx][ny] == 1) continue;
                
                int newCost = cost + 100;
                if(direction != d) newCost += 500;
                
                if(dp[nx][ny][d] > newCost) {
                    dp[nx][ny][d] = newCost;
                    queue.add(new Node(nx, ny, d, newCost));
                }
            }
        }
        
        answer = INF;
        for(int i = 0; i < 4; i++) {
            answer = Math.min(answer, dp[N-1][N-1][i]);
        }
        
        return answer;
    }
    private class Node {
        int x;
        int y;
        int direction;
        int cost;
        
        public Node (int x, int y, int direction, int cost) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.cost = cost;
        }
    }
}