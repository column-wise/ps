import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int chickenCount;
    static List<List<Integer>> comb;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int [][] map = new int[N][N];
        int result = 2500 * 13;
        List<Home> homes = new ArrayList<>();
        List<Chicken> chickens = new ArrayList<>();
        comb = new ArrayList<>();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1){
                    homes.add(new Home(i,j));
                } else if (map[i][j] == 2) {
                    chickens.add(new Chicken(i,j));
                }

            }
        }

        chickenCount = chickens.size();

        for(int i = 0; i < homes.size(); i++){
            Home h = homes.get(i);
            for(int j = 0; j < chickens.size(); j++){
                Chicken c = chickens.get(j);
                h.chickenDist.add(h.getDistance(c.x, c.y));
            }
        }

        combination(0, 0, M, new ArrayList<>());

        for(List<Integer> l : comb){
            int sum = 0;
            for(Home h : homes){
                int min = 2500;
                for(int chickenIdx : l){
                    min = Math.min(min, h.chickenDist.get(chickenIdx));
                }
                sum += min;
            }
            result = Math.min(result, sum);
        }

        System.out.println(result);

    }

    static class Home {
        int x;
        int y;
        List<Integer> chickenDist;

        public Home(int x, int y) {
            this.x = x;
            this.y = y;
            chickenDist = new ArrayList<>();
        }
        int getDistance(int x, int y){
            return Math.abs(this.x-x) + Math.abs(this.y-y);
        }
    }

    static class Chicken {
        int x;
        int y;

        public Chicken(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void combination(int start, int depth, int target, List<Integer> list){
        if(depth == target){
            comb.add(new ArrayList<>(list));
            return;
        }

        for(int i = start; i < chickenCount; i++){
            list.add(i);
            combination(i+1, depth+1, target, list);
            list.remove(list.size()-1);
        }
    }
}