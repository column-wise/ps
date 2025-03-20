import java.util.*;
class Solution {
    static int ret;
    public int solution(int n, int[][] q, int[] ans) {
        ret = 0;
        generateCombinations(n, 0, 1, new int[5], q, ans);
        return ret;
    }
    
    private void generateCombinations(int N, int depth, int cur, int[] seq, int[][] q, int[] ans) {
        if(depth == 5) {
            int count = 0;
            for(int i = 0; i < q.length; i++) {
                if(compareArray(seq, q[i], ans[i])){
                    count++;
                } else {
                    break;
                }
            }
            if(count == q.length) ret++;
            return;
        }
        
        for(int i = cur; i <= N; i++) {
            seq[depth] = i;
            generateCombinations(N, depth+1, i+1, seq, q, ans);
        }
    }
    
    private boolean compareArray(int[] arr1, int[] arr2, int answer) {
        int count = 0;
        int cursor1 = 0;
        int cursor2 = 0;
        
        while(cursor1 < 5 && cursor2 < 5) {
            if (arr1[cursor1] == arr2[cursor2]) {
                cursor1++;
                cursor2++;
                count++;
            } else if (arr1[cursor1] < arr2[cursor2]) {
                cursor1++;
            } else {
                cursor2++;
            }
        }
        
        return count == answer;
    }
}