class Solution {
    public static boolean solution(int[][] key, int[][] lock) {
        int[][] rotatedKey = rotateKey(key);

        for(int r = 0; r < 4; r++) {
            for(int i = 0; i < key.length + lock.length - 1; i++) {
                for(int j = 0; j < key.length + lock.length - 1; j++) {
                    if(putKeyOnBoardAndCheck(rotatedKey, lock, i, j)) {
                        return true;
                    }
                }
            }
            rotatedKey = rotateKey(rotatedKey);
        }

        return false;
    }

    private static int[][] rotateKey(int[][] key) {
        int[][] rotated = new int[key.length][key.length];
        for(int i = 0; i < key.length; i++){
            for(int j = 0; j < key.length; j++){
                rotated[j][key.length - i - 1] = key[i][j];
            }
        }
        return rotated;
    }

    private static boolean putKeyOnBoardAndCheck(int[][] key, int[][] lock, int x, int y) {
        int[][] board = new int[key.length*2 + lock.length - 2][key.length*2 + lock.length - 2];
        for(int i = 0; i < lock.length; i++) {
            for(int j = 0; j < lock.length; j++) {
                board[key.length - 1 + i][key.length - 1 + j] = lock[i][j];
            }
        }

//        System.out.println("board: ");
//        for(int i = 0; i < board.length; i++) {
//            for(int j = 0; j < board.length; j++) {
//                System.out.print(board[i][j]+" ");
//            }
//            System.out.println();
//        }
//        System.out.println();

        for(int i = 0; i < key.length; i++) {
            for(int j = 0; j < key.length; j++) {
                if((board[x+i][y+j] & key[i][j]) == 1) return false;
                board[x+i][y+j] = board[x+i][y+j] | key[i][j];
            }
        }

//        System.out.println("after put key: ");
//        for(int i = 0; i < board.length; i++) {
//            for(int j = 0; j < board.length; j++) {
//                System.out.print(board[i][j]+" ");
//            }
//            System.out.println();
//        }
//        System.out.println();

        return checkBoard(board, key, lock);
    }

    private static boolean checkBoard(int[][] board, int[][] key, int[][] lock) {
        for(int i = key.length - 1; i < key.length + lock.length - 1; i++) {
            for(int j = key.length - 1; j < key.length + lock.length - 1; j++) {
                if(board[i][j] == 0) return false;
            }
        }

        return true;
    }
}