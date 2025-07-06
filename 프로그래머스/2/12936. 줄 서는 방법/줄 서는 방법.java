import java.util.*;
class Solution {
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        int[] index = new int[n];
        k--;
        
        for(int i = n; i > 0; i--) {
            long cases = factorial(i-1);
            index[n-i] = (int)(k / cases);
            k = k % cases;
        }
        
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= n; i++) nums.add(i);

        for (int i = 0; i < n; i++) {
            answer[i] = nums.get(index[i]);
            nums.remove(index[i]);
        }

        return answer;
    }
    
    private long factorial(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) result *= i;
        return result;
    }
}