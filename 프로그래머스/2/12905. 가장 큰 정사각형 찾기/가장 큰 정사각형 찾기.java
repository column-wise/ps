import java.lang.Math;
class Solution
{
    public int solution(int[][] board)
    {
        int answer = 0;
        int rows = board.length;
        int cols = board[0].length;
        int maxSize = Math.min(rows, cols);
        
        int[][] prefixSum = new int[rows+1][cols+1];
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                prefixSum[i][j] = prefixSum[i-1][j] + prefixSum[i][j-1]
                         - prefixSum[i-1][j-1] + board[i-1][j-1];
            }
        }
        
        for(int target = maxSize; target > 0; target--) {
          for(int startX = 0; startX + target <= rows; startX++) {
            for(int startY = 0; startY + target <= cols; startY++) {
              int sum = prefixSum[startX + target][startY + target]
                      - prefixSum[startX][startY + target]
                      - prefixSum[startX + target][startY]
                      + prefixSum[startX][startY];
              if (sum == target * target) {
                return sum;
              }
            }
          }
        }
        
        return answer;
    }
    
}