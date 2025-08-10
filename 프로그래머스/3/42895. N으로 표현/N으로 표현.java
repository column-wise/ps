import java.util.*;

class Solution {
    int[] times = new int[9];
    
    public int solution(int N, int number) {
        int answer = 0;
        int[] dp = new int[100000000];
        times[0] = 0;
        
        Arrays.fill(dp, 9);
        Queue<Integer> queue = new ArrayDeque<>();
        
        String strN = Integer.toString(N);
        String strNum = "";
        
        for(int i = 1; i < 9; i++) {
            strNum += strN;
            int num = Integer.parseInt(strNum);
            times[i] = num;
            dp[num] = i;
            queue.add(num);
        }
        
        System.out.println(Arrays.toString(times));
        
        while(!queue.isEmpty()) {
            int num = queue.poll();
            
            for(int i = 1; i < 9; i++) {

                if(dp[num] + dp[times[i]] > 8) continue;                

                if(num + times[i] >=0 && num + times[i] < 100000000 && dp[num + times[i]] > dp[num] + dp[times[i]]) {
                    dp[num + times[i]] = dp[num] + dp[times[i]];
                    queue.add(num+times[i]);
                }
                
                if(num - times[i] >= 0 && dp[num - times[i]] > dp[num] + dp[times[i]]) {
                    dp[num - times[i]] = dp[num] + dp[times[i]];
                    queue.add(num-times[i]);
                }
                
                if(times[i] - num >= 0 && dp[times[i] - num] > dp[num] + dp[times[i]]) {
                    dp[times[i] - num] = dp[num] + dp[times[i]];
                    queue.add(times[i] - num);
                }
                
                if(num * times[i] >=0 && num * times[i] < 100000000 && dp[num * times[i]] > dp[num] + dp[times[i]]) {
                    dp[num * times[i]] = dp[num] + dp[times[i]];
                    queue.add(num*times[i]);
                }
                
                if(times[i] != 0 && dp[times[i]] != 0 && dp[num / times[i]] > dp[num] + dp[times[i]]) {
                    dp[num / times[i]] = dp[num] + dp[times[i]];
                    queue.add(num/times[i]);
                }
                
                if(num != 0 && dp[num] != 0 && dp[times[i] / num] > dp[num] + dp[times[i]]) {
                    dp[times[i] / num] = dp[num] + dp[times[i]];
                    queue.add(times[i] / num);
                }
            }
        }
        
        return dp[number] < 9 ? dp[number] : -1;
    }
}