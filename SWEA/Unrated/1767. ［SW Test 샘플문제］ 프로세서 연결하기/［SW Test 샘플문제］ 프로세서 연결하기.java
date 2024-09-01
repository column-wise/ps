import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int [][] processor;
    static int [][] copy;
    static int n;
    static List<Core> chosenCores;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static int maxCnt;
    static int minWire;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++){
            n = Integer.parseInt(br.readLine());
            processor = new int[n][n];
            copy = new int[n][n];

            List<Core> cores = new ArrayList<>();
            maxCnt = 0;
            int alreadyConnected = 0;
            minWire = Integer.MAX_VALUE;

            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++){
                    processor[i][j] = Integer.parseInt(st.nextToken());
                    copy[i][j] = processor[i][j];

                    if(processor[i][j] == 1){
                        if(i == 0 || i == n-1 || j == 0 || j == n-1) {
                            alreadyConnected ++;
                        } else{
                            cores.add(new Core(i,j));
                        }
                    }
                }
            }

            int p = cores.size();
            for (Core core : cores) {
                core.find();
            }

            for(int i = (1<<p) - 1; i >= 0; i--){
                chosenCores = new ArrayList<>();
                for(int j = 0; j < p; j++){
                    if((i&(1<<j)) != 0){
                        chosenCores.add(cores.get(j));
                    }
                }

                int chosenCnt = chosenCores.size();
                if(maxCnt <= chosenCnt){
                    connect(new WireRoute[chosenCnt], 0, chosenCnt);
                }
            }
            System.out.println("#"+test_case+" "+minWire);
        }



    }

    public static class Core implements Comparable<Core>{
        int x;
        int y;
        List<WireRoute> wireRoutes;

        public Core(int x, int y){
            this.x = x;
            this.y = y;
            wireRoutes = new ArrayList<>();
        }

        @Override
        public String toString() {
            return "Core{" +
                    "x=" + x +
                    ", y=" + y +
                    ", wireRoutes=" + wireRoutes +
                    '}';
        }

        void find(){
            for(int i = 0; i < 4; i++){
                boolean canConnect = true;
                int nx = x + dx[i];
                int ny = y + dy[i];
                int count = 0;
                while(nx >= 0 && nx < n && ny >= 0 && ny < n){
                    if(processor[nx][ny] != 0){
                        canConnect = false;
                        break;
                    }
                    nx = nx + dx[i];
                    ny = ny + dy[i];
                    count ++;
                }
                if(canConnect){
                    wireRoutes.add(new WireRoute(x, y, i, count));
                }
            }
        }

        @Override
        public int compareTo(Core o) {
            return wireRoutes.size() - o.wireRoutes.size();
        }
    }

    public static class WireRoute {
        int x;
        int y;
        int direction;
        int length;

        public WireRoute(int x, int y, int direction, int length) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.length = length;
        }

        @Override
        public String toString() {
            return "WireRoute{" +
                    "direction=" + direction +
                    ", length=" + length +
                    '}';
        }

        public int drawWire(int x, int y, int length){
            // 연결할 수 없는 경우(장애물)
            if(copy[x][y] == 1){
                return -1;
            }

            // boundary에 도착, 연결 가능
            if(x == 0 || x == n-1 || y == 0 || y == n-1){
                return length;

            // 진행 중
            } else{
                copy[x][y] = 1;
                int ret = drawWire(x+dx[direction], y+dy[direction], length+1);

                // 장애물을 만난 경우 이므로 wire를 설치하지 않는 것으로 되돌려줘야 함
                if(ret == -1){
                    copy[x][y] = 0;
                }
                return ret;
            }
        }
    }

    static void connect(WireRoute[] wireRoutes, int depth, int target) throws InterruptedException {
        if(depth == target){

            for(int i = 0; i < n ;i++){
                for(int j = 0; j < n; j++){
                    copy[i][j] = processor[i][j];
                }
            }

//            for(WireRoute w : wireRoutes){
//                System.out.println(w.toString());
//            }

            int cost = 0;
            boolean canConnect = true;
            for(WireRoute wr : wireRoutes){
                int ret = wr.drawWire(wr.x+dx[wr.direction], wr.y+dy[wr.direction], 1);
                if(ret == -1){
                    canConnect = false;
                    break;
                } else{
                    cost += ret;
                }
            }

            // System.out.println(canConnect);
            if(canConnect){
                // System.out.println("cost: "+cost);
                maxCnt = Math.max(maxCnt, target);
                minWire = Math.min(minWire, cost);
            }
            // Thread.sleep(1000);
            return;
        }

        for(WireRoute wr : chosenCores.get(depth).wireRoutes){
            wireRoutes[depth] = wr;
            connect(wireRoutes, depth+1, target);
        }
    }

}