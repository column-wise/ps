import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[][] map = new int[N][N];
            List<Point> homes = new ArrayList<>();

            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] == 1){
                        homes.add(new Point(i, j));
                    }
                }
            }

            int homeNum = homes.size();
            int[][][] distTable = new int[N][N][homeNum];

            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    for(int d = 0; d < homeNum; d++){
                        distTable[i][j][d] = homes.get(d).getDistance(i,j);
                    }
                }
            }

            int max = 0;
            int maxCount = 0;

            for(int k = 1; k < 2*N; k++){
                int cost = k*k + (k-1)*(k-1);

                for(int i = 0; i < N; i++){
                    for(int j = 0; j < N; j++){
                        int count = 0;
                        for(int d = 0; d < homeNum; d++){
                            if(distTable[i][j][d] <= k-1){
                                //System.out.println(i+" "+j+" "+homes.get(d).toString());
                                count ++;
                            }
                        }

                        int profit = count * M;
                        if(profit-cost >= 0){
                            //System.out.println("i = "+i+" j = "+j+" k = "+ k + " profit - cost = "+(profit-cost) + " count = "+count);
                            maxCount = Math.max(maxCount, count);
                        }

                    }
                }
            }

            System.out.println("#" + test_case + " "+maxCount);

        }
    }

    private static class Point{
        int x;
        int y;

        private Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        private int getDistance(int x, int y){
            return Math.abs(this.x - x) + Math.abs(this.y - y);
        }
    }
}
