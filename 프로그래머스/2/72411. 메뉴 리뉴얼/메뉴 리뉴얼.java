import java.util.*;

class Solution {
    public static String[] solution(String[] orders, int[] course) {
        List<String> answer = new ArrayList<>();
        TreeMap<String, Integer>[] maps = new TreeMap[course.length];
        for (int i = 0; i < course.length; i++) {
            maps[i] = new TreeMap<>();
        }

        for(String order : orders) {
            for(int i = 0; i < course.length; i++) {
                char[] chars = order.toCharArray();
                Arrays.sort(chars);
                combination(chars, "", 0, course[i], maps[i]);
            }
        }

        for(int i = 0; i < course.length; i++) {
            int maxLength = 2;
            List<String> candidates = new ArrayList<>();

            for(Map.Entry<String, Integer> entry : maps[i].entrySet()) {
                if(entry.getValue() > maxLength) {
                    candidates.clear();
                    candidates.add(entry.getKey());
                    maxLength = entry.getValue();
                } else if(entry.getValue() == maxLength) {
                    candidates.add(entry.getKey());
                }
            }

            answer.addAll(candidates);
        }


        String[] ret = answer.toArray(new String[0]);
        Arrays.sort(ret, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                int ret = 0;
                for(int i = 0; i < Math.min(s1.length(), s2.length()); i++) {
                    ret = Character.compare(s1.charAt(i), s2.charAt(i));
                    if(ret != 0) break;
                }
                if(ret == 0) ret = s1.length() - s2.length();
                return ret;
            }
        });

        return ret;
    }

    private static void combination(char[] order, String str, int cur, int target, Map<String, Integer> map) {
        if(str.length() == target) {
            if(map.containsKey(str)) {
                map.put(str, map.get(str) + 1);
            } else {
                map.put(str, 1);
            }
            return;
        } else {
            for(int i = cur; i < order.length; i++) {
                combination(order, str + order[i], i + 1, target, map);
            }
        }
    }
}