import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        for(int i = 0; i < n-1; i++){
            map[i][0] = Integer.parseInt(br.readLine());
        }

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < m; i++){
            map[n-1][i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                int count = 0;
                for(int r = 0; r < n-1; r++){
                    if(map[r][0] == Math.abs(i-r) + j){
                        count ++;
                    } else{
                        break;
                    }
                }
                for(int c = 0; c < m; c++){
                    if(map[n-1][c] == n-1-i + Math.abs(c-j)){
                        count ++;
                    } else{
                        break;
                    }
                }

                if(count == n+m-1){
                    System.out.println((i+1) +" "+(j+1));
                    return;
                }

            }
        }
    }
}