import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<Point> homes = new ArrayList<>();
        List<Point> chickenShops = new ArrayList<>();
        int min = Integer.MAX_VALUE;

        int [][] map = new int[n][n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1){
                    homes.add(new Point(i,j));
                }else if(map[i][j] == 2){
                    chickenShops.add(new Point(i,j));
                }
            }
        }

        for(int i = 0; i < (1<< chickenShops.size()); i++){
            int count = 0;
            List<Integer> choosed = new ArrayList<>();
            for(int j = 0; j < chickenShops.size(); j++){
                if((i&(1<<j)) > 0){
                    count ++;
                    choosed.add(j);
                }
            }

            if(count == m){
                int sum = 0;
                for(Point home: homes){
                    int dist = Integer.MAX_VALUE;
                    for(int j = 0; j < m; j++){
                        dist = Math.min(dist, home.getDistance(chickenShops.get(choosed.get(j))));
                    }
                    sum += dist;
                }
                min = Math.min(min, sum);
            }
        }
        System.out.println(min);
    }

    private static class Point{
        int x;
        int y;

        private Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        private int getDistance(Point p){
            return Math.abs(x-p.x) + Math.abs(y-p.y);
        }
    }
}