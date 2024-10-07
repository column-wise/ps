import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int[][] next;
    static int[] parents;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        int N = Integer.parseInt(inputs[0]);
        int M = Integer.parseInt(inputs[1]);

        String[][] map = new String[N][M];
        for(int i = 0; i < N; i++){
            inputs = br.readLine().split("");
            for(int j = 0; j < M; j++){
                map[i][j] = inputs[j];
            }
        }

        next = new int[N][M];
        parents = new int[N*M];
        for(int i = 0; i < N*M; i++){
            parents[i] = -1;
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j].equals("U")){
                    next[i][j] = M*(i-1)+j;
                } else if(map[i][j].equals("D")){
                    next[i][j] = M*(i+1)+j;
                } else if(map[i][j].equals("L")){
                    next[i][j] = M*i+j - 1;
                } else if(map[i][j].equals("R")){
                    next[i][j] = M*i+j + 1;
                }
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                union(i*M+j, next[i][j]);
            }
        }

        int count = 0;
        for(int p : parents){
            if(p < 0) count++;
        }
        System.out.println(count);
    }

    private static int find(int a){
        if(parents[a] < 0) return a;
        return parents[a] = find(parents[a]);
    }

    private static boolean union(int a, int b){
        int aroot = find(a);
        int broot = find(b);

        if(aroot == broot) return false;
        parents[aroot] += parents[broot];
        parents[broot] = aroot;
        return true;
    }
}