import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static final int INF = 1000000000;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");

        int M = Integer.parseInt(inputs[0]);
        int N = Integer.parseInt(inputs[1]);

        char[][] map = new char[N][M];
        int [][][] visited = new int[N][M][4];
        int startX = -1;
        int startY = -1;
        int endX = -1;
        int endY = -1;

        for(int i = 0; i < N; i++) {
            inputs = br.readLine().split("");
            for(int j = 0; j < M; j++) {
                map[i][j] = inputs[j].charAt(0);
                if(map[i][j] == 'C') {
                    if(startX == -1) {
                        startX = i;
                        startY = j;
                    } else {
                        endX = i;
                        endY = j;
                    }
                }
                for(int d = 0; d < 4; d++) {
                    visited[i][j][d] = INF;
                }
            }
        }

        Queue<Node> queue = new ArrayDeque<>();
        for(int d = 0; d < 4; d++) {
            int nx = startX + dx[d];
            int ny = startY + dy[d];

            if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
            if(map[nx][ny] == '*') continue;
            queue.add(new Node(nx, ny, d, 0));
            visited[nx][ny][d] = 0;
        }

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            int x = node.x;
            int y = node.y;
            int currentDirection = node.currentDirection;
            int turn = node.turn;

            if(x == endX && y == endY) continue;

            for(int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if(map[nx][ny] == '*') continue;
                if (currentDirection != d) {
                    if((currentDirection + d) % 2 == 0) continue;
                    if(visited[nx][ny][d] > turn + 1) {
                        visited[nx][ny][d] = turn + 1;
                        queue.add(new Node(nx, ny, d, turn+1));
                    }
                } else {
                    if(visited[nx][ny][d] > turn) {
                        visited[nx][ny][d] = turn;
                        queue.add(new Node(nx, ny, d, turn));
                    }
                }
            }
        }

        int min = INF;
        for(int d = 0; d < 4; d++) {
            if(visited[endX][endY][d] < min) min = visited[endX][endY][d];
        }

        System.out.println(min);
    }

    private static class Node {
        int x, y;
        int currentDirection;
        int turn;

        public Node (int x, int y, int currentDirection, int turn) {
            this.x = x;
            this.y = y;
            this.currentDirection = currentDirection;
            this.turn = turn;
        }
    }
}