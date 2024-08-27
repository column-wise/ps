import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        List<Route> routes;

        for(int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());
            int[][] map = new int[N][N];
            routes = new ArrayList<>();

            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int a = 0; a < N; a++) {
                for(int b = 0; b < N; b++) {
                    for(int c = 1; c < N-a; c++) {
                        for(int d = 1; d < N-b; d++) {
                            if(a+c+d >= N || b-c < 0 || b+d >= N) {
                                continue;
                            } else {
                                routes.add(new Route(a,b,c,d));
                            }
                        }
                    }
                }
            }

            int max = -1;

            for(int i = 0; i < routes.size(); i++) {
                int count = 0;
                Set<Integer> ateDesserts = new HashSet<>();
                //System.out.println(routes.get(i).toString());
                Route r = routes.get(i);
                int x = r.x;
                int y = r.y;
                ateDesserts.add(map[x][y]);

                for(int d = 1; d <= r.d1; d++) {
                    x++;
                    y--;
                    count++;
                    ateDesserts.add(map[x][y]);
                }

                for(int d = 1; d <= r.d2; d++) {
                    x++;
                    y++;
                    count++;
                    ateDesserts.add(map[x][y]);
                }

                for(int d = 1; d <= r.d1; d++) {
                    x--;
                    y++;
                    count++;
                    ateDesserts.add(map[x][y]);
                }

                for(int d = 1; d <= r.d2; d++) {
                    x--;
                    y--;
                    count++;
                    ateDesserts.add(map[x][y]);
                }
                
                if(ateDesserts.size() != count) {
                    continue;
                } else {
                    max = Math.max(max, count);
                }
            }

            System.out.println("#"+test_case+" "+max);
        }
    }

    public static class Route{
        int x;
        int y;
        int d1;
        int d2;

        @Override
        public String toString() {
            return "Route [x=" + x + ", y=" + y + ", d1=" + d1 + ", d2=" + d2 + "]";
        }

        public Route(int x, int y, int d1, int d2) {
            this.x = x;
            this.y = y;
            this.d1 = d1;
            this.d2 = d2;
        }
    }
}