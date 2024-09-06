import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Integer>[] adjList = new ArrayList[n];
        for(int i = 0; i<n; i++) {
            adjList[i] = new ArrayList<>();
        }

        int[] inDegree = new int[n];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        List<Integer> topologicalSort = new ArrayList<>();

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            adjList[a].add(b);
            inDegree[b]++;
        }


        for(int i = 0; i < n; i++){
            if(inDegree[i] == 0) pq.add(i);
        }

        while(!pq.isEmpty()){
            int v = pq.poll();
            topologicalSort.add(v+1);

            for(int i : adjList[v]){
                inDegree[i]--;
                if(inDegree[i] == 0){
                    pq.add(i);
                }
            }
        }

        for(int i : topologicalSort){
            System.out.print(i+" ");
        }
        System.out.println();

    }
}