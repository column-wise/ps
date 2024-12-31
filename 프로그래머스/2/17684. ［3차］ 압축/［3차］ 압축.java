import java.util.*;

class Solution {
    public static int[] solution(String msg) {
        Map<String, Integer> dict = new HashMap<>();
        for(int i = 0; i < 26; i++) {
            dict.put((char)('A' + i) + "", i+1);
        }

        int index = 27;
        List<Integer> compressed = new ArrayList<>();
        int cursor = 0;

        while(cursor < msg.length()) {
            int size = 1;
            while((cursor + size <= msg.length()) && dict.containsKey(msg.substring(cursor, cursor + size))) {

                size ++;
            }

            if(cursor + size <= msg.length()) {
                dict.put(msg.substring(cursor, cursor+size), index++);
            }
            compressed.add(dict.get(msg.substring(cursor, cursor + size - 1)));

            cursor += size-1;
        }

        int[] answer = new int[compressed.size()];
        for(int i = 0; i < compressed.size(); i++) {
            answer[i] = compressed.get(i);
        }

        return answer;
    }
}