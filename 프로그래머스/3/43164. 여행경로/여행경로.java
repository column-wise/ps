import java.util.*;

class Solution {
    List<String> answer = new ArrayList<>();
    Map<String, PriorityQueue<String>> graph = new HashMap<>();
    
    public String[] solution(String[][] tickets) {
        for(String[] ticket : tickets) {
            String depart = ticket[0];
            String arrival = ticket[1];
            if(!graph.containsKey(depart)) {
                graph.put(depart, new PriorityQueue<>());
            }
            graph.get(depart).add(arrival);
        }
        
        dfs("ICN");
        Collections.reverse(answer);

        return answer.toArray(new String[0]);
    }
    
    private void dfs(String airport) {
        PriorityQueue<String> dests = graph.get(airport);
        
        while(dests != null && !dests.isEmpty()) {
            String next = dests.poll();
            dfs(next);
        }
        
        answer.add(airport);
    }
}