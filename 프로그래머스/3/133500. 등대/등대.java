import java.util.*;

class Solution {
    static List<Integer>[] graph;
    static int[][] dp;
    static boolean[] visited;
    
    public int solution(int n, int[][] lighthouse) {
        int answer = 0;
        graph = new ArrayList[n];
        dp = new int[n][2];
        visited = new boolean[n];
        
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for(int[] edge : lighthouse) {
            int from = edge[0] - 1;
            int to = edge[1] - 1;
            graph[from].add(to);
            graph[to].add(from);
        }
        
        dfs(0);
        
        return Math.min(dp[0][0], dp[0][1]);
    }
    
    private void dfs(int node) {
        visited[node] = true;
        dp[node][0] = 0;
        dp[node][1] = 1;
        
        for(int child : graph[node]) {
            if(!visited[child]) {
                dfs(child);
                
                dp[node][0] += dp[child][1];
                dp[node][1] += Math.min(dp[child][0], dp[child][1]);
            }
        }
    }
    
}