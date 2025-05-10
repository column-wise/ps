import java.util.*;

class Solution {
    int[][] matrix;
    public Integer[] solution(int rows, int columns, int[][] queries) {
        List<Integer> answer = new ArrayList<>();
        
        matrix = new int[rows][columns];
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                matrix[i][j] = i * columns + j +1;
            }
        }
        
        for(int[] query : queries) {
            int min = rotate(query[0] - 1, query[1] - 1, query[2] - 1, query[3] - 1);
            answer.add(min);
        }
        
        return answer.toArray(new Integer[answer.size()]);
    }
    
    private int rotate(int x1, int y1, int x2, int y2) {
        int temp = matrix[x1][y1];
        int min = temp;
        
        for(int i = x1; i < x2; i++) {
            matrix[i][y1] = matrix[i+1][y1];
            min = Math.min(min, matrix[i][y1]);
        }
        
        for(int i = y1; i < y2; i++) {
            matrix[x2][i] = matrix[x2][i+1];
            min = Math.min(min, matrix[x2][i]);
        }
        
        for(int i = x2; i > x1; i--) {
            matrix[i][y2] = matrix[i-1][y2];
            min = Math.min(min, matrix[i][y2]);
        }
        
        for(int i = y2; i > y1; i--) {
            matrix[x1][i] = matrix[x1][i-1];
            min = Math.min(min, matrix[x1][i]);
        }
        
        matrix[x1][y1+1] = temp;
        return min;
    }
}