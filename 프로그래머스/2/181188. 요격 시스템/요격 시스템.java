import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        List<Node> arr = new ArrayList<>();
        
        for(int[] range : targets) {
            arr.add(new Node(range[0], range[1]));
        }
        
        arr.sort(new Comparator<Node>() {
            public int compare(Node o1, Node o2) {
                int ret = Integer.compare(o1.end, o2.end);
                if(ret == 0) {
                    ret = Integer.compare(o1.start, o2.start);
                }
                
                return ret;
            }
        });
        
        int missile = -1;
        for(Node n : arr) {
            if(missile <= n.start) {
                answer++;
                missile = n.end;
            }
        }
        
        return answer;
    }
    
    private class Node {
        int start;
        int end;
        
        public Node (int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}