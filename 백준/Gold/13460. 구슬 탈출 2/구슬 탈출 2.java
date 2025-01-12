import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Main {
    static int N;
    static int M;
    static char[][] board;
    static boolean[][][][] visited;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");

        N = Integer.parseInt(inputs[0]);
        M = Integer.parseInt(inputs[1]);

        board = new char[N][M];
        visited = new boolean[N][M][N][M];
        Deque<Node> queue = new ArrayDeque<>();
        int answer = 0;
        int round = -1;
        boolean found = false;
        boolean isRedIn = false;
        boolean isBlueIn = false;
        int[] TCoord = new int[2];
        int[] RCoord = new int[2];
        int[] BCoord = new int[2];

        for(int i = 0; i < N; i++) {
            inputs = br.readLine().split("");
            for(int j = 0; j < M; j++) {
                if(inputs[j].charAt(0) == '#' || inputs[j].charAt(0) == '.') {
                    board[i][j] = inputs[j].charAt(0);
                } else if(inputs[j].charAt(0) == 'R') {
                    RCoord[0] = i;
                    RCoord[1] = j;
                    board[i][j] = '.';
                } else if(inputs[j].charAt(0) == 'B') {
                    BCoord[0] = i;
                    BCoord[1] = j;
                    board[i][j] = '.';
                } else if(inputs[j].charAt(0) == 'O') {
                    TCoord[0] = i;
                    TCoord[1] = j;
                    board[i][j] = 'O';
                }
            }

        }

        visited[RCoord[0]][RCoord[1]][BCoord[0]][BCoord[1]] = true;
        queue.add(new Node(RCoord[0], RCoord[1], BCoord[0], BCoord[1], 1));
        while(!queue.isEmpty() && !found) {
            Node node = queue.poll();
            for(int d = 0; d < 4; d++) {
                int[] newBCoord = new int[2];
                int[] newRCoord = new int[2];

                if(isBlueFirst(node.Rx, node.Ry, node.Bx, node.By, d)) {
                    newBCoord = move(node.Bx, node.By, d, node.Rx, node.Ry);
                    if(newBCoord[0] == TCoord[0] && newBCoord[1] == TCoord[1]) isBlueIn = true;

                    if(isBlueIn) {
                        newRCoord = move(node.Rx, node.Ry, d, -1, -1);
                    } else {
                        newRCoord = move(node.Rx, node.Ry, d, newBCoord[0], newBCoord[1]);
                    }

                    if(newRCoord[0] == TCoord[0] && newRCoord[1] == TCoord[1] && node.round <= 10) {
                        isRedIn = true;
                    }
                } else {
                    newRCoord = move(node.Rx, node.Ry, d, node.Bx, node.By);
                    if(newRCoord[0] == TCoord[0] && newRCoord[1] == TCoord[1] && node.round <= 10) isRedIn = true;

                    if(isRedIn) {
                        newBCoord = move(node.Bx, node.By, d, -1, -1);
                    } else {
                        newBCoord = move(node.Bx, node.By, d, newRCoord[0], newRCoord[1]);
                    }

                    if(newBCoord[0] == TCoord[0] && newBCoord[1] == TCoord[1]) {
                        isBlueIn = true;
                    }
                }


                if(isRedIn && !isBlueIn) {
                    answer = 1;
                    round = node.round;
                    found = true;
                    break;
                }

                if(visited[newRCoord[0]][newRCoord[1]][newBCoord[0]][newBCoord[1]] || node.round > 10) {
                    continue;
                }

                if(isBlueIn) {
                    isRedIn = false;
                    isBlueIn = false;
                    continue;
                }

                visited[newRCoord[0]][newRCoord[1]][newBCoord[0]][newBCoord[1]] = true;
                queue.add(new Node(newRCoord[0], newRCoord[1], newBCoord[0], newBCoord[1], node.round + 1));
            }
        }
        //System.out.println(answer);
        System.out.println(round);
    }

    private static boolean isBlueFirst(int Rx, int Ry, int Bx, int By, int dir) {
        int x = Rx;
        int y = Ry;

        while(true) {
            x += dx[dir];
            y += dy[dir];

            if(x == Bx && y == By) return true;
            else if(board[x][y] == '#') return false;
        }
    }

    private static int[] move(int x, int y, int dir, int oppositeX, int oppositeY) {
        int[] coords = new int[2];
        int nx = x;
        int ny = y;
        board[x][y] = '.';

        while((board[nx + dx[dir]][ny + dy[dir]] != '#' && (nx + dx[dir] != oppositeX || ny + dy[dir] != oppositeY)) && board[nx][ny] != 'O') {
            nx += dx[dir];
            ny += dy[dir];
        }

        coords[0] = nx;
        coords[1] = ny;

        return coords;
    }

    private static class Node {
        int Rx;
        int Ry;
        int Bx;
        int By;
        int round;

        public Node(int Rx, int Ry, int Bx, int By, int round) {
            this.Rx = Rx;
            this.Ry = Ry;
            this.Bx = Bx;
            this.By = By;
            this.round = round;
        }
    }

    private static void print(int Rx, int Ry, int Bx, int By, int[] TCoord) throws Exception{
        System.out.println();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(i == Rx && j == Ry) System.out.print("R ");
                else if(i == Bx && j == By) System.out.print("B ");
                else if(i == TCoord[0] && j == TCoord[1]) System.out.print("O ");
                else System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}