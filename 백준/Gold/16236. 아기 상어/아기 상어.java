import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        int x = -1;
        int y = -1;
        int size = 2;
        int ate = 0;

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9){
                    map[i][j] = 0;
                    x = i;
                    y = j;
                }
            }
        }

        int time = 0;

        // BFS 탐색에 사용할 큐
        Deque<Point> queue;
        boolean[][] visited;

        // 가장 가까운 먹잇감과의 거리 저장
        int minDist;

        // 먹잇감 우선 순위 관리를 위한 pq
        PriorityQueue<Point> targets;
        while(true){
            queue = new ArrayDeque<>();
            targets = new PriorityQueue<>();
            minDist = N*N;
            visited = new boolean[N][N];
            visited[x][y] = true;
            queue.add(new Point(x,y,0));

            while(!queue.isEmpty()){
                Point cur = queue.poll();
                x = cur.x;
                y = cur.y;
                int dist = cur.dist;

                for(int i = 0; i < 4; i++){
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                    if(visited[nx][ny]) continue;
                    if(map[nx][ny] > size) continue;
                    if(map[nx][ny] < size && map[nx][ny] != 0 && minDist >= dist+1){
                        visited[nx][ny] = true;
                        targets.add(new Point(nx,ny, dist+1));
                        minDist = Math.min(minDist, dist+1);
                    } else if(minDist > dist+1){
                        visited[nx][ny] = true;
                        queue.add(new Point(nx,ny,dist+1));
                    }
                }
            }

            if(targets.isEmpty()){
                break;
            }

            Point target = targets.poll();

            x = target.x;
            y = target.y;
            time += target.dist;
            map[x][y] = 0;

            ate ++;
            if(ate == size){
                ate = 0;
                size ++;
            }
        }
        System.out.println(time);
    }

    static class Point implements Comparable<Point>{
        int x;
        int y;
        int dist;

        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Point o) {
            int res = Integer.compare(dist, o.dist);
            if(res == 0){
                res = Integer.compare(x, o.x);
            }
            if(res == 0){
                res = Integer.compare(y, o.y);
            }
            return res;
        }
    }
}