import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[] parents;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        init();

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                for(int k = 0; k < 8; k++) {
                    int ni = i + dx[k];
                    int nj = j + dy[k];

                    if(ni < 0 || ni >= N || nj < 0 || nj >= M) continue;
                    if(map[i][j] == map[ni][nj]) {
                        union(M*i+j, M*ni+nj);
                    }
                }
            }
        }

        for(int i = N-1; i >= 0; i--) {
            for(int j = M-1; j >= 0; j--) {
                for(int k = 0; k < 8; k++) {
                    int ni = i + dx[k];
                    int nj = j + dy[k];

                    if(ni < 0 || ni >= N || nj < 0 || nj >= M) continue;
                    if(map[i][j] == map[ni][nj]) {
                        union(M*i+j, M*ni+nj);
                    }
                }
            }
        }

        Set<Integer> peaks = new HashSet<>();

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] != 0) {
                    peaks.add(parents[M*i+j]);
                }
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                for(int k = 0; k < 8; k++) {
                    int ni = i + dx[k];
                    int nj = j + dy[k];

                    if(ni < 0 || ni >= N || nj < 0 || nj >= M) continue;
                    if(map[i][j] < map[ni][nj] && peaks.contains(parents[M*i+j])) {
                        peaks.remove(parents[M*i+j]);
                    }
                }

            }
        }

        System.out.println(peaks.size());
    }

    static void init() {
        parents = new int[N*M];
        for(int i = 0; i < N*M; i++) {
            parents[i] = i;
        }
    }

    static int find(int a) {
        if(parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b) {
        int ARoot = find(a);
        int BRoot = find(b);

        if(ARoot == BRoot) return false;
        parents[BRoot] = ARoot;
        return true;
    }
}