import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        int answer = 65536;
        Set<String> str1Set = new HashSet<>();
        Set<String> str2Set = new HashSet<>();
        Map<String, Integer> str1Counter = new HashMap<>();
        Map<String, Integer> str2Counter = new HashMap<>();
        StringBuilder sb;
        
        for(int i = 0; i < str1.length()-1; i++) {
            char c1 = str1.charAt(i);
            char c2 = str1.charAt(i+1);
            if(!Character.isLetter(c1) || !Character.isLetter(c2)) {
                continue;
            }
            
            sb = new StringBuilder();
            sb.append(Character.toLowerCase(c1)).append(Character.toLowerCase(c2));
            String element = sb.toString();
            str1Set.add(element);
            
            str1Counter.put(element, str1Counter.getOrDefault(element, 0) + 1);
        }
        
        for(int i = 0; i < str2.length()-1; i++) {
            char c1 = str2.charAt(i);
            char c2 = str2.charAt(i+1);
            if(!Character.isLetter(c1) || !Character.isLetter(c2)) {
                continue;
            }
            
            sb = new StringBuilder();
            sb.append(Character.toLowerCase(c1)).append(Character.toLowerCase(c2));
            String element = sb.toString();
            str2Set.add(element);
            
            str2Counter.put(element, str2Counter.getOrDefault(element, 0) + 1);
        }
        
        Set<String> unionSet = new HashSet<>();
        Set<String> intersectionSet = new HashSet<>();
        
        unionSet.addAll(str1Set);
        unionSet.addAll(str2Set);
        
        intersectionSet.addAll(str1Set);
        intersectionSet.retainAll(str2Set);
        
        int s = 0;
        int m = 0;
        
        for(String element : intersectionSet) {
            System.out.print(element + " ");
            s += Math.min(str1Counter.get(element), str2Counter.get(element));
        }
        System.out.println();
        
        for(String element : unionSet) {
            System.out.print(element + " ");
            m += Math.max(str1Counter.getOrDefault(element, 0), str2Counter.getOrDefault(element, 0));
        }
        System.out.println();
        
        float similarity = 0.0f;
        
        System.out.println("s : " + s + " m : " + m);
        
        if(m != 0) {
            answer = s * 65536;
            answer /= m;
        }
        return answer;
    }
}