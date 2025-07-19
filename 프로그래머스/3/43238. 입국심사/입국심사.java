import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        long left  = 1;
        long right = (long) times[times.length - 1] * n;
        long answer = right;

        while (left <= right) {
            long mid = (left + right) / 2;
            long people = 0;

            for (int t : times) {
                people += mid / t;
                if (people >= n) break;
            }

            if (people < n) {
                left = mid + 1;
            } else {
                answer = mid;
                right = mid - 1;
            }
        }

        return answer;
    }
}
