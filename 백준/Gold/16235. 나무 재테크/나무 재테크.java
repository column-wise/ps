import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0,1,1,1,0,-1,-1,-1};
    static int[] dy = {1,1,0,-1,-1,-1,0,1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        int[][] nutrient = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                nutrient[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = 5;
            }
        }

        PriorityQueue<Tree> toAbsorb = new PriorityQueue<>();
        PriorityQueue<Tree> absorbed = new PriorityQueue<>();
        PriorityQueue<Tree> temp;
        Deque<Tree> spreading = new ArrayDeque<>();
        Deque<Tree> dead = new ArrayDeque<>();

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());

            toAbsorb.add(new Tree(r,c,age));
        }

        for(int year = 0; year < K; year++){

            // spring
            while(!toAbsorb.isEmpty()){
                Tree tree = toAbsorb.poll();
                if(map[tree.r][tree.c] >= tree.age){
                    map[tree.r][tree.c] -= tree.age;
                    tree.age ++;
                    if(tree.age % 5 == 0){
                        spreading.add(tree);
                    }
                    absorbed.add(tree);
                } else{
                    dead.add(tree);
                }
            }
            temp = toAbsorb;
            toAbsorb = absorbed;
            absorbed = temp;

            // summer
            while(!dead.isEmpty()){
                Tree tree = dead.poll();
                map[tree.r][tree.c] += tree.age/2;
            }

            // autumn
            while(!spreading.isEmpty()){
                Tree tree = spreading.poll();
                for(int i = 0; i < 8; i++){
                    int nr = tree.r + dx[i];
                    int nc = tree.c + dy[i];

                    if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                    toAbsorb.add(new Tree(nr,nc,1));
                }
            }

            //winter
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    map[i][j] += nutrient[i][j];
                }
            }
        }

        System.out.println(toAbsorb.size());
    }

    private static class Tree implements Comparable<Tree>{
        int r;
        int c;
        int age;

        private Tree(int r, int c, int age){
            this.r = r;
            this.c = c;
            this.age = age;
        }

        @Override
        public int compareTo(Tree o) {
            return Integer.compare(age, o.age);
        }
    }
}