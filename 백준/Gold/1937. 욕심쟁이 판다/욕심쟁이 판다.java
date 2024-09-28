import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] forest = new int[N][N];
        List<Point> pointList = new ArrayList<>();
        int[][] dpTable = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                forest[i][j] = Integer.parseInt(st.nextToken());
                pointList.add(new Point(i,j,forest[i][j]));
            }
        }

        // 대나무의 수가 적은 지역부터 오름차순 정렬
        Collections.sort(pointList);
        int max = 0;

        for(int i = 0; i < N*N; i++){
            Point p = pointList.get(i);
            int x = p.x;
            int y = p.y;

            for(int j = 0; j < 4; j++){
                int nx = x + dx[j];
                int ny = y + dy[j];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

                // 주변 칸 중 현재 위치보다 대나무가 적은 칸이 있다면
                if(forest[x][y] > forest[nx][ny]){
                    dpTable[x][y] = Math.max(dpTable[x][y], dpTable[nx][ny] + 1);
                    max = Math.max(max, dpTable[x][y]);
                }
            }
        }

        System.out.println(max+1);

    }

    private static class Point implements Comparable<Point>{
        int x;
        int y;
        int tree;

        private Point(int x, int y, int tree){
            this.x = x;
            this.y = y;
            this.tree = tree;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(tree, o.tree);
        }
    }
}