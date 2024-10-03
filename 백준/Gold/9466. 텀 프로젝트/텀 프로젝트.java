import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[] choose;
    static int visited[];
    static int count;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++){
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            choose = new int[N];
            visited = new int[N];

            for(int i = 0; i < N; i++){
                choose[i] = Integer.parseInt(st.nextToken()) - 1;
            }
            count = 0;
            for(int i = 0; i < N; i++){
                if(visited[i] == 0) {
                    dfs(i);
                }
            }

            sb.append(N-count).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int cur){

        visited[cur] = 1;
        int next = choose[cur];

        if(visited[next] == 0){
            dfs(next);
        } else if (visited[next] == 1){
            int dest = next;
            next = choose[dest];
            count++;
            while(next != dest){
                count ++;
                next = choose[next];
            }
        }

        visited[cur] = 2;
    }
}