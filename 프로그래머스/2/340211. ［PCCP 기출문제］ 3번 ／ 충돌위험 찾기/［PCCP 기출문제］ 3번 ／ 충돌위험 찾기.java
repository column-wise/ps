class Solution {
    public static int solution(int[][] points, int[][] routes) {
        int answer = 0;

        int maxR = 0;
        int maxC = 0;

        for(int i = 0; i < points.length; i++) {
            if(maxR < points[i][0]) maxR = points[i][0];
            if(maxC < points[i][1]) maxC = points[i][1];
        }

        int[][] board = new int[maxR+1][maxC+1];
        Robot[] robots = new Robot[routes.length];
        for (int i = 0; i < routes.length; i++) {
            robots[i] = new Robot(i, points[routes[i][0] - 1][0], points[routes[i][0] - 1][1]);
            board[points[routes[i][0] - 1][0]][points[routes[i][0] - 1][1]] ++;
        }

        answer += collapseCount(board);
        //print(board);

        while(true) {
            int movedRobot = 0;
            for(int i = 0; i < routes.length; i++) {
//                System.out.println(robots[i].n+": x = "+robots[i].x+" y = "+robots[i].y);
//                if(robots[i].target < routes[i].length) {
//                    System.out.println("target X = " + points[routes[i][robots[i].target] - 1][0]);
//                    System.out.println("target Y = " + points[routes[i][robots[i].target] - 1][1]);
//                }
//                System.out.println();

                if(robots[i].move(board, points, routes[i])) movedRobot ++;
            }

            //print(board);

            if(movedRobot == 0) break;

            answer += collapseCount(board);
        }

        return answer;
    }

    private static class Robot {
        int n;
        int x, y;
        int target = 1;

        public Robot(int n, int x, int y) {
            this.n = n;
            this.x = x;
            this.y = y;
        }

        public boolean move(int[][] board, int[][] points, int[] route) {

            if(target >= route.length) {
                if(x != -1 && y != -1) {
                    board[x][y] --;
                    x = -1;
                    y = -1;
                }
                return false;
            }

            int targetX = points[route[target] - 1][0];
            int targetY = points[route[target] - 1][1];

            board[x][y] --;

            if(x != targetX) {
                x += (targetX - x) / Math.abs(targetX - x);
            } else {
                y += (targetY - y) / Math.abs(targetY - y);
            }

            board[x][y] ++;

            if(isArrived(points, route)) target++;

            return true;
        }

        public boolean isArrived(int[][] points, int[] route) {
            if(x == points[route[target] - 1][0] && y == points[route[target] - 1][1]) {
                return true;
            } else {
                return false;
            }
        }
    }

    private static int collapseCount(int[][] board) {
        int count = 0;
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] > 1) {
                    count++;
                }
            }
        }
        return count;
    }

    private static void print(int[][] board) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}