import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 1000000;
    static int[][] relation;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        relation = new int[n][n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                relation[i][j] = INF;
                if(i == j){
                    relation[i][j] = 0;
                }
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        while(a != -1 || b != -1){
            a--;
            b--;
            relation[a][b] = 1;
            relation[b][a] = 1;

            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
        }

        floyd_warshall();

        int min = INF;
        for(int i = 0; i < n; i++){
            int max = 0;
            for(int j = 0; j < n; j++){
                max = Math.max(max, relation[i][j]);
            }
            min = Math.min(min, max);
        }

        List<Integer> candidates = new ArrayList<>();

        for(int i = 0; i < n; i++){
            int max = 0;
            for(int j = 0; j < n; j++){
                max = Math.max(max, relation[i][j]);
            }
            if(max == min){
                candidates.add(i);
            }
        }

        System.out.println(min+" "+candidates.size());
        for(int i = 0; i < candidates.size(); i++){
            System.out.print(candidates.get(i)+1+" ");
        }

    }

    public static void floyd_warshall(){
        for(int k = 0; k < n; k++){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(relation[i][j] > relation[i][k] + relation[k][j]){
                        relation[i][j] = relation[i][k] + relation[k][j];
                    }
                }
            }
        }
    }

}