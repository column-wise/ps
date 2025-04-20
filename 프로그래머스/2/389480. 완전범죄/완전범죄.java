class Solution {
    public int solution(int[][] info, int n, int m) {
        int itemCnt = info.length;
        // dp[w] -> b가 w만큼 사용했을 때 세이브된 a 의 최댓값
        int[] dp = new int[m];
        int totalA = 0;

        for (int[] it : info) {
            int a = it[0], b = it[1];
            totalA += a;
            for (int w = m - 1; w >= b; w--) {
                dp[w] = Math.max(dp[w], dp[w - b] + a);
            }
        }
        int minA = totalA - dp[m - 1];

        return (minA >= n) ? -1 : minA;
    }
}