import java.util.*;
import java.lang.Math;
class Solution {
    public long solution(long k, long d) {
        long answer = 0;
        List<Long> possibles = new ArrayList<>();
        int limit = (int)(d/k);
        
        for(int i = 0; i <= limit; i++) {
            possibles.add(i * k);
        }
        
        for(int i = 0; i <= limit; i++) {
            long x = possibles.get(i);
            long y = (long)Math.sqrt(d*d - x*x);
            answer += (y/k) + 1;
        }
        
        return answer;
    }
}