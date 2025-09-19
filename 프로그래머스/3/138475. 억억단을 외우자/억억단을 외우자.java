class Solution {
    public int[] solution(int e, int[] starts) {
        int[] answer = new int[starts.length];
        int[] cnt = new int[e+1];
        int[] bests = new int[e+1];
        
        for(int i = 1; i <= e; i++) {
            int j = 1;
            while(i * j <= e) {
                cnt[i*j] ++;
                j++;
            }
        }
        
        int maxIdx = e;
        for(int i = e; i >= 1; i--) {
            if(cnt[i] >= cnt[maxIdx]) {
                maxIdx = i;
            }
            bests[i] = maxIdx;
        }
        
        
        for(int i = 0; i < starts.length; i++) {
            int s = starts[i];
            answer[i] = bests[s];
        }
        return answer;
    }
}