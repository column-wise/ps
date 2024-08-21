import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int n;
    static int[][] map;
    static int direction;
    static int[] dx = new int[] { 0, 0, -1, 1 };
    static int[] dy = new int[] { -1, 1, 0, 0 };

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            String input = st.nextToken();

            if (input.equals("left")) {
                direction = 0;
            } else if (input.equals("right")) {
                direction = 1;
            } else if (input.equals("up")) {
                direction = 2;
            } else {
                direction = 3;
            }

            map = new int[n][n];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            slide();

            System.out.println("#"+test_case);
            for(int a = 0; a < n; a++){
                for(int b = 0; b < n; b++){
                    System.out.print(map[a][b]+" ");
                }
                System.out.println();
            }
        }
    }

    public static void slide() {
        int x;
        int y;
        int xlimit;
        int ylimit;

        if(direction == 0){
            x = 0;
            y = 0;
            xlimit = n-1;
            ylimit = n-1;
            for(int i = x; i <= xlimit; i++) {
                for (int j = y; j <= ylimit; j++) {
                    int nx = i - dx[direction];
                    int ny = j - dy[direction];
                    while (0 <= nx && nx < n && 0 <= ny && ny < n && map[nx][ny] == 0) {
                        nx -= dx[direction];
                        ny -= dy[direction];
                    }

                    if (0 <= nx && nx < n && 0 <= ny && ny < n && map[i][j] == map[nx][ny]) {
                        map[i][j] *= 2;
                        map[nx][ny] = 0;
                    }
                }
            }
            for(int i = x; i <= xlimit; i++){
                for(int j = y; j <= ylimit; j++){
                    if(map[i][j] == 0) {
                        int nx = i;
                        int ny = j;
                        while (0 <= ny && ny < n - 1 && map[nx][ny] == 0) {
                            ny++;
                        }
                        map[i][j] = map[nx][ny];
                        map[nx][ny] = 0;
                    }
                }
            }
        } else if (direction ==1) {
            x = 0;
            y = n-1;
            xlimit = n-1;
            ylimit = 0;
            for(int i = x; i <= xlimit; i++) {
                for (int j = y; j > ylimit; j--) {
                    int nx = i - dx[direction];
                    int ny = j - dy[direction];
                    while (0 <= nx && nx < n && 0 <= ny && ny < n && map[nx][ny] == 0) {
                        nx -= dx[direction];
                        ny -= dy[direction];
                    }

                    if (0 <= nx && nx < n && 0 <= ny && ny < n && map[i][j] == map[nx][ny]) {
                        map[i][j] *= 2;
                        map[nx][ny] = 0;
                    }
                }
            }
            for(int i = x; i <= xlimit; i++){
                for(int j = y; j > ylimit; j--){
                    if(map[i][j] == 0) {
                        int nx = i;
                        int ny = j;
                        while (0 < ny && ny <= n - 1 && map[nx][ny] == 0) {
                            ny--;
                        }
                        map[i][j] = map[nx][ny];
                        map[nx][ny] = 0;
                    }
                }
            }
        } else if (direction == 2) {
            x = 0;
            y = 0;
            xlimit = n-1;
            ylimit = n-1;
            for (int j = y; j <= ylimit; j++) {
                 for(int i = x; i <= xlimit; i++){
                    int nx = i - dx[direction];
                    int ny = j - dy[direction];
                    while (0 <= nx && nx < n && 0 <= ny && ny < n && map[nx][ny] == 0) {
                        nx -= dx[direction];
                        ny -= dy[direction];
                    }
                    if (0 <= nx && nx < n && 0 <= ny && ny < n && map[i][j] == map[nx][ny]) {
                        map[i][j] *= 2;
                        map[nx][ny] = 0;
                    }
                }
            }

            for(int j = y; j <= ylimit; j++){
                for(int i = x; i <= xlimit; i++){
                    if(map[i][j] == 0) {
                        int nx = i;
                        int ny = j;
                        while (0 <= nx && nx < n - 1 && map[nx][ny] == 0) {
                            nx++;
                        }
                        map[i][j] = map[nx][ny];
                        map[nx][ny] = 0;
                    }
                }
            }

        } else {
            x = n - 1;
            y = 0;
            xlimit = 0;
            ylimit = n - 1;
            for (int j = y; j <= ylimit; j++) {
                for(int i = x; i > xlimit; i--) {
                    int nx = i - dx[direction];
                    int ny = j - dy[direction];
                    while (0 <= nx && nx < n && 0 <= ny && ny < n && map[nx][ny] == 0) {
                        nx -= dx[direction];
                        ny -= dy[direction];
                    }

                    if (0 <= nx && nx < n && 0 <= ny && ny < n && map[i][j] == map[nx][ny]) {
                        map[i][j] *= 2;
                        map[nx][ny] = 0;
                    }
                }
            }
            for(int j = y; j <= ylimit; j++){
                for(int i = x; i > xlimit; i--){
                    if(map[i][j] == 0) {
                        int nx = i;
                        int ny = j;
                        while (0 < nx && nx <= n - 1 && map[nx][ny] == 0) {
                            nx--;
                        }
                        map[i][j] = map[nx][ny];
                        map[nx][ny] = 0;
                    }
                }
            }
        }



    }
}