import java.util.*;
class Solution {
    static StringTokenizer st;
        public static String solution(String play_time, String adv_time, String[] logs) {

        int arrSize = getSec(play_time);
        long[] diff = new long[arrSize+1];
        long[] prefixSum = new long[arrSize+1];

        int advSec = getSec(adv_time);

        for(int i = 0; i < logs.length; i++) {
            st = new StringTokenizer(logs[i], "-");
            String startStr = st.nextToken();
            String endStr = st.nextToken();
            int start = getSec(startStr);
            int end = getSec(endStr);

            diff[start] ++;
            diff[end] --;
        }

        for(int i = 1; i < diff.length; i++) {
            diff[i] += diff[i-1];
        }

        for(int i = 1; i <= arrSize; i++) {
            prefixSum[i] = prefixSum[i-1] + diff[i-1];
        }

        int maxIdx = 0;
        long maxSec = -1;

        for(int i = 0; i <= arrSize - advSec; i++) {
            long sec = prefixSum[i + advSec ] - prefixSum[i];
            if(sec > maxSec) {
                maxSec = sec;
                maxIdx = i;
            }
        }

        return getString(maxIdx);
    }

    private static int getSec(String time) {
        int sec = 0;
        st = new StringTokenizer(time, ":");
        sec += 3600 * Integer.parseInt(st.nextToken());
        sec += 60 * Integer.parseInt(st.nextToken());
        sec += Integer.parseInt(st.nextToken());

        return sec;
    }

    private static String getString(int time) {
        String str = "";
        str += String.format("%02d", time/3600);
        str += ":" + String.format("%02d",time%3600/60);
        str += ":" + String.format("%02d", time%60);

        return str;
    }
}