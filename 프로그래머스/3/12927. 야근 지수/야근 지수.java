import java.util.*;
import java.lang.Math;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Long> pq = new PriorityQueue<>((a, b) -> Long.compare(b, a));
        
        for(int work : works) {
            pq.add((long)work);
        }
        
        for(int i = 0; i < n && !pq.isEmpty(); i++) {
            Long work = pq.poll();
            work -= 1;
            if(work == 0) continue;
            pq.add(work);
        }
        
        while(!pq.isEmpty()) {
            answer += Math.pow(pq.poll(), 2);
        }
        
        return answer;
    }
}