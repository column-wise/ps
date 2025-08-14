class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        startday--;
        int N = schedules.length;
        
        for(int i = 0; i < N; i++) {
            int wish = schedules[i];
            int[] arrives = timelogs[i];
            
            int limit = toMinutes(wish) + 10;
            int count = 0;
            
            for(int d = 0; d < 7; d++) {
                int day = (d + startday) % 7;
                if(day >= 5) continue;
                
                int arrived = toMinutes(arrives[d]);
                if(limit >= arrived) {
                    System.out.println("day " + day + " employer " + i + " arrived " + arrived);
                    count ++;
                }
            }
            
            if(count == 5) answer++;
        }
        
        return answer;
    }
    
    public int toMinutes(int timeFormat) {
        int H = timeFormat / 100;
        int M = timeFormat % 100;
        
        return H*60 + M;
    }
}