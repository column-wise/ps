import java.util.*;

class Solution {
    public static int[] solution(int[][] edges) {
        int[] answer = new int[4];
        int V = -1;

        for(int[] edge : edges) {
            V = Math.max(V, Math.max(edge[0], edge[1]));
        }
        List<Integer>[] graph = new ArrayList[V+1];
        int[] inDegree = new int[V+1];
        int[] outDegree = new int[V+1];

        for(int[] edge : edges) {
            if(graph[edge[0]] == null) graph[edge[0]] = new ArrayList<>();
            if(graph[edge[1]] == null) graph[edge[1]] = new ArrayList<>();
        }

        for(int[] edge : edges) {
            outDegree[edge[0]] ++;
            inDegree[edge[1]] ++;
        }

        int newNode = -1;

        for(int i = 1; i <= V; i++) {
            if(inDegree[i] == 0 && outDegree[i] >= 2) {
                newNode = i;
                break;
            }
        }

        answer[0] = newNode;

        int graphs = outDegree[newNode];

        for(int[] edge : edges) {
            if(edge[0] == newNode) {
                inDegree[edge[1]] --;
            }
        }

        for(int i = 1; i <= V; i++) {
            if(i == newNode) continue;
            if(graph[i] == null) continue;

            if(inDegree[i] == 2 && outDegree[i] == 2) {
                answer[3]++;
            } else if(inDegree[i] == 0) {
                answer[2]++;
            }
        }

        answer[1] = graphs - answer[2] - answer[3];

        return answer;
    }
}