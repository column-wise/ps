import java.util.*;
class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;

        int[][] mask = new int[board.length+1][board[0].length+1];
        for(int[] s : skill) {
            // s[0]: type, s[1]: r1, s[2]: c1, s[3]: r2, s[4]: c2, s[5]: degree
            if(s[0] == 1) {
                mask[s[3]+1][s[4]+1] -= s[5];
                mask[s[3]+1][s[2]] += s[5];
                mask[s[1]][s[4]+1] += s[5];
                mask[s[1]][s[2]] -= s[5];
            } else {
                mask[s[3]+1][s[4]+1] += s[5];
                mask[s[3]+1][s[2]] -= s[5];
                mask[s[1]][s[4]+1] -= s[5];
                mask[s[1]][s[2]] += s[5];
            }
        }
        
        for (int i = 0; i < mask.length; i++) {
            for (int j = 1; j < board[0].length; j++) {
                mask[i][j] += mask[i][j - 1];
            }
        }

        for (int j = 0; j < board[0].length; j++) {
            for (int i = 1; i < mask.length; i++) {
                mask[i][j] += mask[i - 1][j];
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] += mask[i][j];
                if (board[i][j] > 0) answer++;
            }
        }
        
        return answer;
    }
}