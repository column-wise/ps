import java.util.*;

class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        int maxPlayTime = 0;
        String mConv = convert(m);

        for (String info : musicinfos) {
            String[] parts = info.split(",");
            String start = parts[0], end = parts[1];
            String title = parts[2], sheet = parts[3];

            int playTime = calcTime(start, end);

            String sheetConv = convert(sheet);
            String played = buildPlayed(sheetConv, playTime);

            if (played.contains(mConv) && playTime > maxPlayTime) {
                maxPlayTime = playTime;
                answer = title;
            }
        }

        return answer;
    }

    private String convert(String s) {
        return s.replace("C#", "c")
                .replace("D#", "d")
                .replace("F#", "f")
                .replace("G#", "g")
                .replace("A#", "a")
                .replace("B#", "b");
    }

    private int calcTime(String start, String end) {
        String[] s1 = start.split(":"), s2 = end.split(":");
        int sh = Integer.parseInt(s1[0]), sm = Integer.parseInt(s1[1]);
        int eh = Integer.parseInt(s2[0]), em = Integer.parseInt(s2[1]);
        return (eh * 60 + em) - (sh * 60 + sm);
    }

    private String buildPlayed(String sheet, int playTime) {
        StringBuilder sb = new StringBuilder();
        int len = sheet.length();
        for (int i = 0; i < playTime; i++) {
            sb.append(sheet.charAt(i % len));
        }
        return sb.toString();
    }
}
