import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[][] notebook;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        notebook = new int[N][M];

        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int[][] sticker = new int[R][C];

            for(int r = 0; r < R; r++){
                st = new StringTokenizer(br.readLine());
                for(int c = 0; c < C; c++){
                    sticker[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] rotatedSticker = sticker;
            boolean canPlaceSticker;
            for(int j = 0; j < 4; j++){
                canPlaceSticker = findStickerPlace(rotatedSticker);

                if(!canPlaceSticker){
                    sticker = rotatedSticker;
                    R = rotatedSticker.length;
                    C = rotatedSticker[0].length;

                    int rotatedC = R;
                    int rotatedR = C;

                    rotatedSticker = new int[rotatedR][rotatedC];
                    for(int r = 0; r < R; r++){
                        for(int c = 0; c < C; c++){
                            rotatedSticker[c][R-r-1] = sticker[r][c];
                        }
                    }
                } else {
                    break;
                }
            }

        }

        int count = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(notebook[i][j] == 1){
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    static boolean findStickerPlace(int[][] sticker){
        int R = sticker.length;
        int C = sticker[0].length;
        boolean foundStickerPlacement = false;

        for(int startR = 0; startR <= N-R; startR++){
            for(int startC = 0; startC <= M-C; startC++){
                boolean canPlaceSticker = true;
                for(int r = 0; r < R; r++){
                    for(int c = 0; c < C; c++){
                        if((notebook[startR+r][startC+c] & sticker[r][c]) == 1){
                            canPlaceSticker = false;
                            break;
                        }
                    }
                    if(!canPlaceSticker){
                        break;
                    }
                }
                if(canPlaceSticker){
                    foundStickerPlacement = true;
                    for(int r = 0; r < R; r++){
                        for(int c = 0; c < C; c++){
                            notebook[startR+r][startC+c] = (notebook[startR+r][startC+c] | sticker[r][c]);
                        }
                    }
                }
                if(foundStickerPlacement){
                    break;
                }
            }
            if(foundStickerPlacement){
                break;
            }
        }

        return foundStickerPlacement;
    }
}