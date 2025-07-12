import java.util.*;
class Solution {
    public int solution(int cacheSize, String[] cities) {
        for(int i = 0; i < cities.length; i++) {
            cities[i] = cities[i].toLowerCase();
        }
        
        int answer = 0;
        LinkedHashMap<String, Integer> cache = new LinkedHashMap<>(cacheSize, 0.75f, true){
            protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
                return super.size() > cacheSize;
            }
        };
        
        for(int i = 0; i < cities.length; i++) {
            String city = cities[i];
            
            if(cache.containsKey(city)) {
                answer += 1;
            } else {
                answer += 5;
            }
            cache.put(city, 1);
        }
        return answer;
    }
}