import java.util.*;

class Solution {
    private int[] parents;
    private int size;
    private int maxGap = 0;
    private final int INF = Integer.MAX_VALUE;
    public int solution(int[] stones, int k) {
        int passengers = 0;
        size = stones.length;
        init();
        
        PriorityQueue<Stone> pq = new PriorityQueue<>();
        for(int i = 0; i < size; i++) {
            pq.add(new Stone(i, stones[i]));
        }
                
        while(!pq.isEmpty()) {
            passengers = pq.peek().durability;
            while(!pq.isEmpty() && pq.peek().durability - passengers <= 0) {
                Stone s = pq.poll();
                parents[s.num] = -1;
                
                int leftGap = 1;
                int rightGap = 1;
                if(s.num > 0) {
                    leftGap = Math.max(maxGap, union(s.num-1, s.num));
                }
                
                if(s.num < size - 1) {
                    rightGap = Math.max(maxGap, union(s.num, s.num+1));
                }
                
                maxGap = Math.max(maxGap, Math.max(leftGap, rightGap));
            }
            
            if(maxGap >= k) break;
            passengers++;
        }
        
        return passengers;
    }
    
    private void init() {
        parents = new int[size];
        for(int i = 0; i < size; i++) {
            parents[i] = INF;
        }
    }
    
    private int find(int a) {
        if(parents[a] == INF) return INF;
        if(parents[a] < 0) return a;
        return parents[a] = find(parents[a]);
    }
    
    private int union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        
        if(aRoot == INF || bRoot == INF || aRoot == bRoot) return 1;
        parents[aRoot] += parents[bRoot];
        parents[bRoot] = aRoot;
        return -parents[aRoot];
    }
    
    private class Stone implements Comparable<Stone>{
        int num;
        int durability;
        
        Stone(int num, int durability) {
            this.num = num;
            this.durability = durability;
        }
        
        public int compareTo(Stone o) {
            return Integer.compare(durability, o.durability);
        }
    }
}