import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] trust;
    static List<List<Integer>> adjList = new ArrayList<>();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        for(int i = 0; i < N; i++){
            adjList.add(new ArrayList<>());
        }
        boolean[] visited;
        trust = new int[N];
        int max = 0;

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            adjList.get(a).add(b);
        }

        for(int i = 0; i < N; i++) {
            visited = new boolean[N];
            dfs(i, visited);
        }

        for(int i = 0; i < N; i++){
            if(trust[i] > max) max = trust[i];
        }

        for(int i = 0; i < N; i++) {
            if(trust[i] == max) {
                System.out.print((i+1)+" ");
            }
        }

    }

    private static void dfs(int cur, boolean[] visited) {
        visited[cur] = true;

        for(int linked : adjList.get(cur)){
            if(!visited[linked]){
                trust[linked] ++;
                dfs(linked, visited);
            }
        }

    }
}