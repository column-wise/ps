import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 1;
        Arrays.sort(targets, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int ret = o1[0] - o2[0];
                if(ret == 0) {
                    ret = o1[1] - o2[1];
                }
                
                return ret;
            }
        });
        
        int s = targets[0][0];
        int e = targets[0][1];
        
        for(int i = 1; i < targets.length; i++) {
            if(targets[i][0] >= s && targets[i][0] < e) {
                s = targets[i][0];
                if(targets[i][1] < e) {
                    e = targets[i][1];
                }
            } else {
                s = targets[i][0];
                e = targets[i][1];
                answer++;
            }
        }
        return answer;
    }
}