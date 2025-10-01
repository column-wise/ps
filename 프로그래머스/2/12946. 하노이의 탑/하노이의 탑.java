import java.util.*;

class Solution {
    List<int[]> moves = new ArrayList<>();

    public int[][] solution(int n) {
        hanoi(n, 1, 2, 3);
        return moves.toArray(new int[0][]);
    }

    public void hanoi(int n, int from, int via, int to) {
        if (n == 1) {
            moves.add(new int[]{from, to});
            return;
        }
        hanoi(n - 1, from, to, via);
        moves.add(new int[]{from, to});
        hanoi(n - 1, via, from, to);
    }
}