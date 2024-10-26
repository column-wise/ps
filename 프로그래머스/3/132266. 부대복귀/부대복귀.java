import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        for(int i = 0; i < sources.length; i++){
            answer[i] = -1;
        }
        
        List<Integer>[] adjList = new ArrayList[n+1];
        for(int i = 0; i <= n; i++){
            adjList[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < roads.length; i++){
            int from = roads[i][0];
            int to = roads[i][1];
            
            adjList[from].add(to);
            adjList[to].add(from);
        }
        
        boolean visited[] = new boolean[n+1];
        Deque<Node> queue = new ArrayDeque<>();
        queue.add(new Node(destination, 0));
        visited[destination] = true;
        
        while(!queue.isEmpty()){
            Node cur = queue.poll();
            
            for(int i = 0; i < sources.length; i++){
                if(sources[i] == cur.region){
                    answer[i] = cur.dist;
                }
            }
            
            for(int nextRegion : adjList[cur.region]){
                if(visited[nextRegion]) continue;
                visited[nextRegion] = true;
                
                queue.add(new Node(nextRegion, cur.dist + 1));

            }
        }
               
        return answer;
    }
    
    private static class Node{
        int region;
        int dist;
        
        private Node(int region, int dist){
            this.region = region;
            this.dist = dist;
        }
    }
    
}