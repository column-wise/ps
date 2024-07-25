import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static boolean found = false;
    static List<Integer> l = new ArrayList<>();
    static int n;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<List<Integer>> adjList = new ArrayList<>();

        for(int i = 0; i < n; i++){
            adjList.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i ++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }

        for(int i = 0; i < n; i++){
            l.add(i);
            DFS(adjList, i, new boolean[n]);
            l.remove((Integer) i);
            if (found) break;
        }

        System.out.println(found ? 1 : 0);
    }

    static void DFS(List<List<Integer>> adjList, int x, boolean[] visited){
        if(l.size() == 5) {
            found = true;
            return;
        }

        visited[x] = true;
        for(int friend : adjList.get(x)){
            if(!visited[friend]){
                l.add(friend);
                DFS(adjList, friend, visited);
                if(found){
                    return;
                }
                l.remove((Integer) friend);
            }
        }
        visited[x] = false;
    }
}
