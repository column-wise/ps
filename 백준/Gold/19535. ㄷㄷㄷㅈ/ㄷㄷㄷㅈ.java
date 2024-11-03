import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        List<Integer>[] adjList = new ArrayList[N+1];
        for(int i = 0; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for(int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adjList[from].add(to);
            adjList[to].add(from);
        }

        long GCount = 0;
        long DCount = 0;
        boolean[] visited = new boolean[N+1];

        for(int i = 1; i <= N; i++) {
            GCount += findGTree(adjList, i);

            visited[i] = true;
            DCount += findDTree(adjList, i, visited);
        }

        if(DCount == GCount * 3) {
            System.out.println("DUDUDUNGA");
        } else if(DCount > GCount * 3) {
            System.out.println("D");
        } else {
            System.out.println("G");
        }

    }

    private static long findGTree (List<Integer>[] adjList, int vertex) {
        long size = adjList[vertex].size();
        return size*(size-1)*(size-2)/6;
    }

    private static long findDTree (List<Integer>[] adjList, int vertex, boolean[] visited) {
        long count = 0;
        long size = adjList[vertex].size();
        visited[vertex] = true;

        for(int neighbor : adjList[vertex]) {
            if(!visited[neighbor]) {
                count += (size - 1) * (adjList[neighbor].size() - 1);
            }
        }

        return count;
    }
}