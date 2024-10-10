import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int X = Integer.parseInt(st.nextToken());

            int[][] map = new int[N][N];
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] mapForRowCheck = new int[N][N];
            int[][] mapForColCheck = new int[N][N];
            boolean[][] slopeForRow = new boolean[N][N];
            boolean[][] slopeForCol = new boolean[N][N];

            int count = 0;
            for(int i = 0; i < N; i++) {
                mapForRowCheck[i] = map[i].clone();
                mapForColCheck[i] = map[i].clone();
            }

            for(int i = 0; i < N; i++){
                boolean isRowPossible = true;
                for(int j = 0; j < N-1; j++){
                    if(mapForRowCheck[i][j+1] - mapForRowCheck[i][j] == 1){
                        for(int k = 0; k < X; k++){
                            if(j-k < 0 || mapForRowCheck[i][j-k] != mapForRowCheck[i][j] || slopeForRow[i][j-k]){
                                isRowPossible = false;
                                break;
                            }
                        }
                    }
                    else if(mapForRowCheck[i][j+1] - mapForRowCheck[i][j] == -1){
                        for(int k = 1; k <= X; k++){
                            if(j+k>=N || mapForRowCheck[i][j+k] != mapForRowCheck[i][j+1]){
                                isRowPossible = false;
                                break;
                            }
                        }
                        if(isRowPossible){
                            for(int k = 1; k <= X; k++){
                                slopeForRow[i][j+k] = true;
                            }
                            j += X-1;
                        }
                    }
                    else if(mapForRowCheck[i][j+1] - mapForRowCheck[i][j] == 0){
                        continue;
                    } else{
                        isRowPossible = false;
                    }
                }
                if(isRowPossible) {
                    count++;
                }
            }

            for(int i = 0; i < N; i++){
                boolean isColPossible = true;
                for(int j = 0; j < N-1; j++){
                    if(mapForColCheck[j+1][i] - mapForColCheck[j][i] == 1){
                        for(int k = 0; k < X; k++){
                            if(j-k < 0 || mapForColCheck[j-k][i] != mapForColCheck[j][i] || slopeForCol[j-k][i]){
                                isColPossible = false;
                                break;
                            }
                        }
                    }
                    else if(mapForColCheck[j+1][i] - mapForColCheck[j][i] == - 1){
                        for(int k = 1; k <= X; k++){
                            if(j+k >= N || mapForColCheck[j+k][i] != mapForColCheck[j+1][i]){
                                isColPossible = false;
                                break;
                            }
                        }
                        if(isColPossible){
                            for(int k = 1; k <= X; k++){
                                slopeForCol[j+k][i] = true;
                            }
                            j += X-1;
                        }
                    }
                    else if(mapForColCheck[j+1][i] - mapForColCheck[j][i] == 0){
                        continue;
                    }
                    else{
                        isColPossible = false;
                    }
                }
                if(isColPossible){
                    count++;
                }
            }
            System.out.println("#"+test_case+" "+count);
        }
    }
}
