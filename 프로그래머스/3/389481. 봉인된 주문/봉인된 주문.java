import java.util.*;

class Solution {
    static List<Character> alphabets = new ArrayList<>();

    public String solution(long n, String[] bans) {
        for (int i = 0; i < 26; i++) {
            alphabets.add((char) ('a' + i));
        }

        // bans를 숫자로 변환 후 정렬
        List<Long> banList = new ArrayList<>();
        for (String ban : bans) {
            banList.add(base26ToLong(ban));
        }
        Collections.sort(banList);

        n--; // 0-based로 조정
        for (long banned : banList) {
            if (banned <= n) {
                n++;
            }
        }

        return longToBase26(n);
    }

    private long base26ToLong(String b26) {
        long result = 0;
        long base = 1;

        for (int i = b26.length() - 1; i >= 0; i--) {
            char alphabet = b26.charAt(i);
            result += (alphabet - 'a' + 1) * base;
            base *= 26;
        }

        return result - 1; // 0-based 보정
    }

    private String longToBase26(long n) {
        StringBuilder sb = new StringBuilder();
        n++; // 1-based 복원

        while (n > 0) {
            n--; // 1-based → 0-based
            sb.append((char) ('a' + (n % 26)));
            n /= 26;
        }

        return sb.reverse().toString();
    }
}
