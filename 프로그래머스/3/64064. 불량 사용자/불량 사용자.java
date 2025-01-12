import java.util.*;
class Solution {
    static Set<String> uniqueCombinations;
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        List<Integer>[] filter = new ArrayList[banned_id.length];
        uniqueCombinations = new HashSet<>();
        for(int i = 0; i < banned_id.length; i++) {
            filter[i] = new ArrayList<>();
        }

        for(int i = 0; i < banned_id.length; i++) {
            for(int j = 0; j < user_id.length; j++) {
                if(banned_id[i].length() != user_id[j].length()) continue;
                for(int k = 0; k < banned_id[i].length(); k++) {
                    if(banned_id[i].charAt(k) != user_id[j].charAt(k) && banned_id[i].charAt(k) != '*') {
                        break;
                    }

                    if(k == banned_id[i].length() - 1) {
                        filter[i].add(j);
                    }
                }
            }
        }

        combination(filter, user_id, new HashSet<>(), 0, filter.length);

        return uniqueCombinations.size();
    }

    private static void combination(List<Integer>[] filter, String[] user_id, Set<String> comb, int cur, int target) {
        if(cur == target) {
            List<String> sorted = new ArrayList<>(comb);
            Collections.sort(sorted);
            String key = String.join(",", sorted);
            uniqueCombinations.add(key);
            return;
        }

        for(int i = 0; i < filter[cur].size(); i++) {
            if(!comb.contains(user_id[filter[cur].get(i)])) {
                comb.add(user_id[filter[cur].get(i)]);
                combination(filter, user_id, comb, cur + 1, target);
                comb.remove(user_id[filter[cur].get(i)]);
            }
        }
    }
}