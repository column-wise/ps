import java.util.*;

class Solution {
    Map<String, String> lang2code;
    Map<String, String> part2code;
    Map<String, String> career2code;
    Map<String, String> food2code;
    Map<String, List<Integer>> applicants;
    
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        StringTokenizer st;
        init();
        
        for(int i = 0; i < info.length; i++) {
            st = new StringTokenizer(info[i], " ");
            String lang = st.nextToken();
            String part = st.nextToken();
            String career = st.nextToken();
            String food = st.nextToken();
            int score = Integer.parseInt(st.nextToken());
            
            addApplicant(lang, part, career, food, score);
        }
        
        for (List<Integer> list : applicants.values()) Collections.sort(list);
        
        for(int i = 0; i < query.length; i++) {
            st = new StringTokenizer(query[i], " ");
            String lang = st.nextToken();
            st.nextToken();
            String part = st.nextToken();
            st.nextToken();
            String career = st.nextToken();
            st.nextToken();
            String food = st.nextToken();
            int score = Integer.parseInt(st.nextToken());
            
            String langCode = lang2code.get(lang);
            String partCode = part2code.get(part);
            String careerCode = career2code.get(career);
            String foodCode = food2code.get(food);
            String filter = langCode + partCode + careerCode + foodCode;
            List<Integer> scores = applicants.get(filter);

            int size = scores.size();
            int lowerbound = binarySearch(scores, score);
            answer[i] = size - lowerbound;
        }
        return answer;
    }
    
    private void init() {
        lang2code = new HashMap<>();
        part2code = new HashMap<>();
        career2code = new HashMap<>();
        food2code = new HashMap<>();
        applicants = new HashMap<>();
        
        lang2code.put("-", "0");
        lang2code.put("cpp" , "1");
        lang2code.put("java", "2");
        lang2code.put("python", "3");
        
        part2code.put("-", "0");
        part2code.put("backend", "1");
        part2code.put("frontend", "2");
        
        career2code.put("-", "0");
        career2code.put("junior", "1");
        career2code.put("senior", "2");
        
        food2code.put("-", "0");
        food2code.put("pizza", "1");
        food2code.put("chicken", "2");
        
        for(String l : lang2code.values()) {
            for(String p : part2code.values()) {
                for(String c : career2code.values()) {
                    for(String f : food2code.values()) {
                        String filter = l+p+c+f;
                        applicants.put(filter, new ArrayList<>());
                    }
                }
            }
        }
    }
                 
    private void addApplicant(String lang, String part, String career, String food, int score) {
        String langCode = lang2code.get(lang);
        String partCode = part2code.get(part);
        String careerCode = career2code.get(career);
        String foodCode = food2code.get(food);
        
        for(String l : lang2code.values()) {
            if(!l.equals(langCode) && !l.equals("0")) continue;
            for(String p : part2code.values()) {
                if(!p.equals(partCode) && !p.equals("0")) continue;
                for(String c : career2code.values()) {
                    if(!c.equals(careerCode) && !c.equals("0")) continue;
                    for(String f : food2code.values()) {
                        if(!f.equals(foodCode) && !f.equals("0")) continue;
                        String filter = l+p+c+f;
                        applicants.get(filter).add(score);
                    }
                }
            }
        }
    }
    
    private int binarySearch(List<Integer> scores, int score) {
        int N = scores.size();
        
        int left = 0;
        int right = N-1;
        
        while(left <= right) {
            int mid = (left + right) / 2;
            int midValue = scores.get(mid);
            
            if(midValue < score) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return left;
    }
}