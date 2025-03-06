import java.util.*;

class Solution {
    static final int INF = Integer.MAX_VALUE;
    static int[] parents;
    static int n;
    public int solution(int[] stones, int k) {
        
        n = stones.length;
        int maxGap = 0;
        int time = 1;
        int passed = 0;
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        init();
        
        for(int i = 0; i < n; i++) {
            pq.add(new Node(i, stones[i]));
        }
        
        while(maxGap < k) {
            while(!pq.isEmpty() && pq.peek().durability - time <= 0) {
                Node node = pq.poll();
                int index = node.index;
                
                parents[index] = -1;

                if(index > 0 && parents[index-1] != INF) {
                    union(index-1, index);
                }

                if(index < n - 1 && parents[index+1] != INF) {
                    union(index, index+1);
                }

                if(maxGap < -parents[find(index)]) {
                    maxGap = -parents[find(index)];
                    if(node.durability == time) passed = node.durability;
                }                
            }
            
            if(maxGap >= k) break;
            
            passed = time;
            time = pq.peek().durability;
        }
        
        return passed;
    }
    
    private static class Node implements Comparable<Node> {
        int index;
        int durability;
        
        public Node (int index, int durability) {
            this.index = index;
            this.durability = durability;
        }
        
        public int compareTo(Node o) {
            return this.durability - o.durability;
        }
    }
    
    private static void init() {
        parents = new int[n];
        
        for(int i = 0; i < n; i++) {
            parents[i] = INF;
        }
    }
    
    private static int find (int a) {
        if(parents[a] < 0) return a;
        return parents[a] = find(parents[a]);
    }
    
    private static boolean union (int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        
        if(aRoot == bRoot) return false;
        
        parents[aRoot] += parents[bRoot];
        parents[bRoot] = aRoot;
        return true;
    }
}