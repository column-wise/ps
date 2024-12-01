import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    public static void main(String [] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];
        int[][] cloud = new int[N][N];

        cloud[N-1][0] = -1;
        cloud[N-1][1] = -1;
        cloud[N-2][0] = -1;
        cloud[N-2][1] = -1;

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int k = 0; k < M; k++) {
            st = new StringTokenizer(br.readLine());
            int direction = Integer.parseInt(st.nextToken()) - 1;
            int distance = Integer.parseInt(st.nextToken());
            
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(cloud[i][j] < 0) {
                        int ni = i + (dx[direction] * distance);
                        if(ni < 0) {
                            while(ni < 0) {
                                ni += N;
                            }
                        } else if(ni >= N) {
                            while(ni >= N) {
                                ni -= N;
                            }
                        }

                        int nj = j + (dy[direction] * distance);
                        if(nj < 0) {
                            while(nj < 0) {
                                nj += N;
                            }
                        } else if(nj >= N) {
                            while(nj >= N) {
                                nj -= N;
                            }
                        }

                        if(cloud[ni][nj] < 0) {
                            cloud[ni][nj] --;
                        } else {
                            cloud[ni][nj] ++;
                        }
                        cloud[i][j] ++;
                        map[ni][nj] ++;
                    }
                }
            }

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(cloud[i][j] < 0) {
                        cloud[i][j] = 1;
                    }
                }
            }

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(cloud[i][j] > 0) {
                        for(int d = 0; d < 4; d++) {
                            int ni = i + dx[2*d+1];
                            int nj = j + dy[2*d+1];

                            if(ni < 0 || ni >= N || nj < 0 || nj >= N) continue;
                            if(map[ni][nj] > 0) map[i][j] ++;
                        }
                    }
                }
            }

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(cloud[i][j] != 1 && map[i][j] >= 2) {
                        cloud[i][j] = -1;
                        map[i][j] -= 2;
                    } else if(cloud[i][j] == 1) {
                        cloud[i][j] = 0;
                    }
                }
            }
        }

        int sum = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                sum += map[i][j];
            }
        }

        System.out.println(sum);
    }
}