import java.util.*;

class Solution {
    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};
    final int INF = 100000;
    public int solution(String[] str) {
        int answer = 0;
        int r = str.length;
        int c = str[0].length();
        
        char[][] board = new char[r][c];
        int[][] visited = new int[r][c];
        
        int startX = -1;
        int startY = -1;
        int endX = -1;
        int endY = -1;
        
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                board[i][j] = str[i].charAt(j);
                if(board[i][j] == 'R') {
                    startX = i;
                    startY = j;
                } else if(board[i][j] == 'G') {
                    endX = i;
                    endY = j;
                }
                visited[i][j] = INF;
            }
        }
        
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(startX, startY, 0));
        visited[startX][startY] = 0;
        
        while(!q.isEmpty()) {
            Node n = q.poll();
            int curX = n.x;
            int curY = n.y;
            int curTurn = n.turn;
            // System.out.println(curX + " " + curY + " " + curTurn);
            
            for(int d = 0; d < 4; d++) {
                int newX = curX;
                int newY = curY;
                
                while(0 <= newX + dx[d] && newX + dx[d] < r && 0 <= newY + dy[d] && newY + dy[d] < c && board[newX+dx[d]][newY+dy[d]] != 'D') {
                    newX += dx[d];
                    newY += dy[d];
                }
                
                if(visited[newX][newY] > curTurn + 1) {
                    visited[newX][newY] = curTurn + 1;
                    q.add(new Node(newX, newY, curTurn + 1));
                }
            }
        }
        
        return visited[endX][endY] != INF ? visited[endX][endY] : -1;
    }
    
    private class Node {
        int x;
        int y;
        int turn;
        
        public Node (int x, int y, int turn) {
            this.x = x;
            this.y = y;
            this.turn = turn;
        }
    }
}