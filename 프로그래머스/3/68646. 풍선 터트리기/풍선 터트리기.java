import java.util.*;
class Solution {
    public static int solution(int[] a) {
        // 배열 길이가 1이면 유일한 풍선이 안전하므로 1을 반환합니다.
        if(a.length == 1) return 1;

        int[] leftMin = new int[a.length];
        int[] rightMin = new int[a.length];
        int safeCount = 0;

        // 양쪽 끝은 무조건 안전한 풍선입니다.
        safeCount += 2; 

        leftMin[1] = a[0];
        for(int i = 2; i < a.length; i++) {
            leftMin[i] = Math.min(leftMin[i-1], a[i-1]);
        }

        rightMin[a.length - 2] = a[a.length - 1];
        for(int i = a.length - 3; i >= 0; i--) {
            rightMin[i] = Math.min(rightMin[i+1], a[i+1]);
        }

        // 내부 풍선에 대해 검사합니다.
        for(int i = 1; i < a.length - 1; i++) {
            if(a[i] <= leftMin[i] || a[i] <= rightMin[i]) {
                safeCount++;
            }
        }

        return safeCount;
    }

}