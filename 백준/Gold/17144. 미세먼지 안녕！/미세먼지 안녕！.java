import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R;
    static int C;
    static int[][] room;
    static int cleanerR1, cleanerR2;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        cleanerR1 = -1;
        cleanerR2 = -1;

        room = new int[R][C];
        for(int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < C; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
                if(room[i][j] == -1) {
                    if(cleanerR1 == -1) {
                        cleanerR1 = i;
                    } else {
                        cleanerR2 = i;
                    }
                }
            }
        }

        for(int t = 0; t < T; t++) {
            diffuse();

//            for(int i = 0; i < R; i++) {
//                for(int j = 0; j < C; j++) {
//                    System.out.print(room[i][j] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();
            clean();

//            for(int i = 0; i < R; i++) {
//                for(int j = 0; j < C; j++) {
//                    System.out.print(room[i][j] + " ");
//                }
//                System.out.println();
//            }
        }

        int count = 0;
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(room[i][j] == -1) continue;
                count += room[i][j];
            }
        }

        System.out.println(count);

    }

    private static void diffuse() {
        int[][] diff = new int[R][C];

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(room[i][j] != 0) {
                    int dust = room[i][j]/5;
                    for(int k = 0; k < 4; k++) {
                        int ni = i + dx[k];
                        int nj = j + dy[k];

                        if(ni < 0 || ni >= R || nj < 0 || nj >= C || (nj == 0 && (ni == cleanerR1 || ni == cleanerR2))) continue;

                        diff[ni][nj] += dust;
                        diff[i][j] -= dust;
                    }
                }
            }
        }

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                room[i][j] += diff[i][j];
            }
        }
    }

    private static void clean() {
        room[cleanerR1-1][0] = 0;
        room[cleanerR2+1][0] = 0;

        for(int i = cleanerR1-1; i > 0; i--) {
            room[i][0] = room[i-1][0];
        }

        for(int i = cleanerR2+1; i < R-1; i++) {
            room[i][0] = room[i+1][0];
        }

        for(int i = 0; i < C-1; i++) {
            room[0][i] = room[0][i+1];
            room[R-1][i] = room[R-1][i+1];
        }

        for(int i = 0; i < cleanerR1; i++) {
            room[i][C-1] = room[i+1][C-1];
        }

        for(int i = R-1; i > cleanerR2; i--) {
            room[i][C-1] = room[i-1][C-1];
        }

        for(int i = C-1; i > 1; i--) {
            room[cleanerR1][i] = room[cleanerR1][i-1];
            room[cleanerR2][i] = room[cleanerR2][i-1];
        }

        room[cleanerR2-1][1] = 0;
        room[cleanerR2][1] = 0;
    }
}