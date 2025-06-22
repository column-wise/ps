import java.lang.Math;
import java.util.*;
class Solution {
    public int solution(int[] mats, String[][] park) {
        int N = park.length;
        int M = park[0].length;
        
        int maxVacuum = 0;
        
        // dp[i][j] = (i,j)를 오른쪽 아래 꼭짓점으로 할 때 빈 정사각형 최대 변의 길이
        int[][] dp = new int[N][M];
        for(int i = 0; i < N; i++) {
            if(park[i][0].equals("-1")) {
                dp[i][0] = 1;
            }
        }
        
        for(int j = 0; j < M; j++) {
            if(park[0][j].equals("-1")) {
                dp[0][j] = 1;
            }
        }
        
        for(int i = 1; i < N; i++) {
            for(int j = 1; j < M; j++) {
                if(park[i][j].equals("-1")) {
                    int min = Math.min(dp[i-1][j], dp[i][j-1]);
                    min = Math.min(min, dp[i-1][j-1]);
                    
                    dp[i][j] = min+1;
                    maxVacuum = Math.max(maxVacuum, min+1);
                }
            }
        }
        
        Arrays.sort(mats);
        int prev = -1;
        for(int i = 0; i < mats.length; i++) {
            if(maxVacuum < mats[i]) {
                break;
            } else {
                prev = mats[i];
            }
        }
        return prev;
    }
}