import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int N;
    static int[][] adjMatrix;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++){
            N = Integer.parseInt(br.readLine());
            int M = Integer.parseInt(br.readLine());

            int ans = 0;

            adjMatrix = new int[N][N];

            for(int i = 0; i < M; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                adjMatrix[a][b] = 1;
            }

            for(int i = 0; i < N; i++){
                if(gtBfs(i) + ltBfs(i) == N-1){
                    ans++;
                }
            }

            System.out.println("#"+test_case+" "+ans);
        }

    }

    private static int gtBfs(int start){
        int cnt = 0;
        Deque<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N];

        queue.add(start);
        visited[start] = true;

        while(!queue.isEmpty()){
            int cur = queue.poll();
            for(int i = 0; i < N; i++){
                if(!visited[i] && adjMatrix[cur][i] != 0){
                    queue.offer(i);
                    visited[i] = true;
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static int ltBfs(int start){
        int cnt = 0;
        Deque<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N];

        queue.add(start);
        visited[start] = true;

        while(!queue.isEmpty()){
            int cur = queue.poll();
            for(int i = 0; i < N; i++){
                if(!visited[i] && adjMatrix[i][cur] != 0){
                    queue.offer(i);
                    visited[i] = true;
                    cnt++;
                }
            }
        }
        return cnt;
    }

}