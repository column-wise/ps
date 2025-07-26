import java.util.*;

class Solution {
    final int daysOfYear = 12 * 28;
    final int daysOfMonth = 28;
    public int[] solution(String today, String[] terms, String[] privacies) {
        StringTokenizer st;
        List<Integer> expired = new ArrayList<>();
        
        int intToday = 0;
        st = new StringTokenizer(today, ".");
        intToday += Integer.parseInt(st.nextToken()) * daysOfYear;
        intToday += Integer.parseInt(st.nextToken()) * daysOfMonth;
        intToday += Integer.parseInt(st.nextToken());
        
        Map<String, Integer> terms2Validity = new HashMap<>();
        for(String term : terms) {
            st = new StringTokenizer(term);
            terms2Validity.put(st.nextToken(), Integer.parseInt(st.nextToken()));
        }
        
        for(int i = 0; i < privacies.length; i++) {
            st = new StringTokenizer(privacies[i], " ");
            String date = st.nextToken();
            String term = st.nextToken();
            
            st = new StringTokenizer(date, ".");
            int intExpiryDay = 0;
            intExpiryDay += Integer.parseInt(st.nextToken()) * daysOfYear;
            intExpiryDay += Integer.parseInt(st.nextToken()) * daysOfMonth;
            intExpiryDay += Integer.parseInt(st.nextToken());
            
            intExpiryDay += terms2Validity.get(term) * daysOfMonth;
            
            if(intExpiryDay <= intToday) {
                expired.add(i+1);
            }
        }
        
        int[] answer = new int[expired.size()];
        for(int i = 0; i < expired.size(); i++) {
            answer[i] = expired.get(i);
        }
        return answer;
    }
}