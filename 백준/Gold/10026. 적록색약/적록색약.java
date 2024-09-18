import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        char[][] originalPaint = new char[N][N];
        char[][] colorWeakness = new char[N][N];
        int[] originalParents = new int[N*N];
        int[] colorWeaknessParents = new int[N*N];

        for(int i = 0; i < N*N; i++){
            originalParents[i] = -1;
            colorWeaknessParents[i] = -1;
        }

        for(int i = 0; i < N; i++){
            char[] inputs = br.readLine().toCharArray();
            for(int j = 0; j < N; j++){
                originalPaint[i][j] = inputs[j];
                if(originalPaint[i][j] == 'B'){
                    colorWeakness[i][j] = 'B';
                } else{
                    colorWeakness[i][j] = 'G';
                }
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){

                for(int k = 0; k < 4; k++){
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

                    if(originalPaint[i][j] == originalPaint[nx][ny]){
                        union(originalParents, i*N+j, nx*N+ny);
                    }

                    if(colorWeakness[i][j] == colorWeakness[nx][ny]){
                        union(colorWeaknessParents, i*N+j, nx*N+ny);
                    }
                }
            }
        }

        for(int i = N-1; i >= 0; i--){
            for(int j = N-1; j >= 0; j--){

                for(int k = 0; k < 4; k++){
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

                    if(originalPaint[i][j] == originalPaint[nx][ny]){
                        union(originalParents, i*N+j, nx*N+ny);
                    }

                    if(colorWeakness[i][j] == colorWeakness[nx][ny]){
                        union(colorWeaknessParents, i*N+j, nx*N+ny);
                    }
                }
            }
        }

        int originalCount = 0;
        int colorWeaknessCount = 0;

        for(int i = 0; i < N*N; i++){
            if(originalParents[i] < 0){
                originalCount++;
            }
            if(colorWeaknessParents[i] < 0){
                colorWeaknessCount++;
            }
        }

        System.out.println(originalCount+" "+colorWeaknessCount);

    }

    private static int find(int[] parents, int a){
        if(parents[a] < 0) return a;
        return parents[a] = find(parents, parents[a]);
    }

    private static boolean union(int[] parents, int a, int b){
        int rootA = find(parents, a);
        int rootB = find(parents, b);

        if(rootA == rootB) return false;

        parents[rootA] += parents[rootB];
        parents[rootB] = rootA;
        return false;
    }
}