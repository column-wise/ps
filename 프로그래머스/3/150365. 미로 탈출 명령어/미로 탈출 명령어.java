import java.util.*;

class Solution {
    static String[] dir = {"d", "l", "r", "u"};
    static int[] dx = {1,0,0,-1};
    static int[] dy = {0,-1,1,0};
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "impossible";
        
        if(Math.abs(x-r) + Math.abs(y-c) <= k && (k - Math.abs(x-r) + Math.abs(y-c)) % 2 == 0) {
            String route = "";
            boolean flag = true;
            int curX = x;
            int curY = y;
            int curMove = 0;
            
            while(curX != r || curY != c || curMove != k) {
                int d = canMove(n, m, curX, curY, r, c, k, curMove);
                if(d != -1) {
                    route += dir[d];
                    curX += dx[d];
                    curY += dy[d];
                    curMove++;
                } else {
                    flag = false;
                    break;
                }
            }
            
            if(flag) {
                answer = route;
            }
        }
        System.out.println(answer);
        return answer;
    }
    
    public int canMove(int n, int m, int curX, int curY, int destX, int destY, int k, int curMove) {
        int ret = -1;
        
        for(int i = 0; i < 4; i++) {
            int nx = curX + dx[i];
            int ny = curY + dy[i];
            
            if(nx <= 0 || nx > n || ny <= 0 || ny > m) continue;
            if(Math.abs(destX - nx) + Math.abs(destY - ny) > k - (curMove + 1)) continue;
            
            ret = i;
            System.out.println("curX : " + curX + " curY : " + curY + " dir : " + dir[i]);
            break;
        }
        
        return ret;
    }
}