class Solution {
    public long solution(int[] diffs, int[] times, long limit) {
        int size = diffs.length;
        long left = 1;
        long right = 1_000_000_000_000_000L;
        long mid = -1;
        
        
        while(left <= right){
            mid = (left + right) / 2;
            long timeSpent = 0;
            
            for(int i = 0; i < size; i++) {
                if(diffs[i] <= mid) {
                    timeSpent += times[i];
                } else {
                    timeSpent += (diffs[i] - mid) * (times[i] + times[i-1]) + times[i];
                }
            }
            
            System.out.println(mid+" "+timeSpent);
            
            if(timeSpent <= limit) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}