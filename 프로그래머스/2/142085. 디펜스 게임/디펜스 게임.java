import java.util.*;

class Solution {
public static int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

        for (int i = 0; i < enemy.length; i++) {
            n -= enemy[i];
            pq.add(enemy[i]);

            if (n < 0) { // 병력 부족
                if (k > 0) { // 무적권 사용
                    n += pq.poll(); // 가장 큰 병력을 무적권으로 방어
                    k--;
                } else {
                    return i; // 방어 실패
                }
            }
        }
        return enemy.length; // 모든 라운드 방어 성공

    }
}