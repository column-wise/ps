import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        Deque<Long> q1 = new ArrayDeque<>();
        Deque<Long> q2 = new ArrayDeque<>();
        
        long sum1 = 0;
        long sum2 = 0;
        for(int i = 0; i < queue1.length; i++){
            q1.add((long)queue1[i]);
            sum1 += queue1[i];
            
            q2.add((long)queue2[i]);
            sum2 += queue2[i];
        }
        
        long target = (sum1 + sum2) / 2;
        if(sum1 + sum2 % 2 == 1){
            return -1;
        }
        
        long poll = -1;
        while(sum1 != target){
            if(sum1 > target){
                poll = q1.poll();
                sum1 -= poll;
                
                q2.offer(poll);
                sum2 += poll;
                answer++;
            } else if(sum1 < target){
                poll = q2.poll();
                sum2 -= poll;
                
                q1.offer(poll);
                sum1 += poll;
                answer++;
            }
            
            if(answer > queue1.length * 2 + 2){
                answer = -1;
                break;
            }
        }
        
        return answer;
    }
}