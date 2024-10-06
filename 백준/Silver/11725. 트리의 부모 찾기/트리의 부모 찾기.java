import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] parents = new int[N+1];
        boolean[] visited = new boolean[N+1];
        Deque<Node> queue = new ArrayDeque<>();
        List<Integer>[] adjList = new ArrayList[N+1];

        for(int i = 0; i < N+1; i++){
            adjList[i] = new ArrayList<>();
        }

        for(int i = 0; i < N-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adjList[a].add(b);
            adjList[b].add(a);
        }

        queue.add(new Node(1, 0));
        visited[1] = true;

        while(!queue.isEmpty()){
            Node cur = queue.poll();
            int n = cur.n;
            int depth = cur.depth;

            for(int neighbor : adjList[n]){
                if(!visited[neighbor]){
                    parents[neighbor] = n;
                    visited[neighbor] = true;
                    queue.add(new Node(neighbor, depth+1));
                }
            }
        }

        for(int i = 2; i < N+1; i++){
            System.out.println(parents[i]);
        }
    }
    private static class Node{
        int n;
        int depth;

        public Node(int n, int depth) {
            this.n = n;
            this.depth = depth;
        }
    }
}