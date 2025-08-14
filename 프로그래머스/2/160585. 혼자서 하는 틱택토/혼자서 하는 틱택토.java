class Solution {
    public int solution(String[] board) {
        int answer = 1;
        int oCount = 0;
        int xCount = 0;
        int oBingo = 0;
        int xBingo = 0;
        
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(board[i].charAt(j) == 'O') oCount++;
                else if(board[i].charAt(j) == 'X') xCount++;
            }
        }
        
        if(oCount - xCount > 1) return 0;
        if(oCount < xCount) return 0;
        
        for(int i = 0; i < 3; i++) {
            int result = checkRow(board, i);
            if(result == 1) oBingo++;
            else if(result == -1) xBingo++;
        }
        
        for(int j = 0; j < 3; j++) {
            int result = checkColumn(board, j);
            if(result == 1) oBingo++;
            else if(result == -1) xBingo++;
        }
        
        int result = checkCross(board);
        if(result == 1) oBingo++;
        else if(result == -1) xBingo++;
        
        result = checkCross2(board);
        if(result == 1) oBingo++;
        else if(result == -1) xBingo++;
        
        if (oBingo > 0 && xBingo > 0) return 0;

        if (oBingo > 0 && oCount == xCount) return 0;
        if (xBingo > 0 && oCount > xCount) return 0;
        
        return answer;
    }
    
    public int checkRow(String[] board, int row) {
        if(board[row].charAt(0) == board[row].charAt(1) && board[row].charAt(1) == board[row].charAt(2)) {
            if(board[row].charAt(0) == 'O') return 1;
            else if(board[row].charAt(0) == 'X') return -1;
        }
        return 0;
    }
    
    public int checkColumn(String[] board, int col) {
        if(board[0].charAt(col) == board[1].charAt(col) && board[1].charAt(col) == board[2].charAt(col)) {
            if(board[0].charAt(col) == 'O') return 1;
            else if(board[0].charAt(col) == 'X') return -1;
        }
        return 0;
    }
    
    public int checkCross(String[] board) {
        if(board[0].charAt(0) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(2)) {
            if(board[0].charAt(0) == 'O') return 1;
            else if(board[0].charAt(0) == 'X') return -1;
        }
        return 0;
    }
    
    public int checkCross2(String[] board) {
        if(board[2].charAt(0) == board[1].charAt(1) && board[1].charAt(1) == board[0].charAt(2)) {
            if(board[2].charAt(0) == 'O') return 1;
            else if(board[2].charAt(0) == 'X') return -1;
        }
        return 0;
    }
}