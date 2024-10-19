import java.util.*;

class Solution {
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    public int solution(String[] maps) {
        int answer = -1;
        
        int N = maps.length;
        int M = maps[0].length();
        int startX = -1;
        int startY = -1;
        int endX = -1;
        int endY = -1;
        int leverX = -1;
        int leverY = -1;
        char[][] map = new char[N][M];
        Deque<Point> queue = new ArrayDeque<>();
        boolean visited[][] = new boolean[N][M];
        boolean isLeverPulled = false;
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                map[i][j] = maps[i].charAt(j);
                if(map[i][j] == 'S'){
                    startX = i;
                    startY = j;
                } else if(map[i][j] == 'L'){
                    leverX = i;
                    leverY = j;
                } else if(map[i][j] == 'E'){
                    endX = i;
                    endY = j;
                }
            }
        }
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        
        queue.add(new Point(startX, startY, 0));
        visited[startX][startY] = true;
        
        while(!queue.isEmpty()){
            Point cur = queue.poll();
            int x = cur.x;
            int y = cur.y;
            int depth = cur.depth;
            
            System.out.println(x+" "+y+" "+depth);
            
            if(x == leverX && y == leverY){
                isLeverPulled = true;
                visited = new boolean[N][M];
                visited[x][y] = true;
                queue.clear();
            }
            
            if(x == endX && y == endY && isLeverPulled){
                answer = depth;
                break;
            }
            
            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if(map[nx][ny] == 'X') continue;
                if(visited[nx][ny]) continue;
                
                visited[nx][ny] = true;
                queue.add(new Point(nx, ny, depth+1));
            }
        }
        
        return answer;
    }
    
    private static class Point{
        int x;
        int y;
        int depth;
        
        private Point(int x, int y, int depth){
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }
}