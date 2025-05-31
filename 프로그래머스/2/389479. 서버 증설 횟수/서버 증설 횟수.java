import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        Queue<Integer> returnTime = new ArrayDeque<>();
        int capability = m-1;
        int curTime = 0;
        
        for(int time = 0; time < players.length; time ++) {
            while(!returnTime.isEmpty() && returnTime.peek() <= time) {
                returnTime.poll();
                capability -= m;
            }
            
            if(players[time] > capability) {
                int needs = (players[time] - capability - 1) / m + 1;
                for(int i = 0; i < needs; i++) {
                    returnTime.offer(time + k);
                    capability += m;
                    answer++;
                }
            }
        }
        return answer;
    }
}