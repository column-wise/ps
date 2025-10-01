import java.util.*;

class Solution {
    int N;
    public int solution(int n, int[][] results) {
        N = n;
        int answer = 0;
        
        List<Integer>[] adjList = new ArrayList[n+1];
        List<Integer>[] revList = new ArrayList[n+1];
        int[] wins = new int[n+1];
        int[] loses = new int[n+1];
        
        for(int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
            revList[i] = new ArrayList<>();
        }
        
        for(int[] match : results) {
            int winner = match[0];
            int loser = match[1];
            adjList[winner].add(loser);
            revList[loser].add(winner);
        }
        
        for(int i = 1; i <= n; i++) {
            wins[i] = bfs(adjList, i);
            loses[i] = bfs(revList, i);
        }
        
        for(int i = 1; i <= n; i++) {
            if(wins[i] + loses[i] == n-1) {
                answer++;
            }
        }
        
        return answer;
    }
    
    private int bfs(List<Integer>[] graph, int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N+1];
        
        queue.add(start);
        visited[start] = true;
        int canReach = 0;
        
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            for(int next : graph[cur]) {
                if(!visited[next]) {
                    queue.add(next);
                    visited[next] = true;
                    canReach++;
                }
            }
        }
        
        return canReach;
    }
}