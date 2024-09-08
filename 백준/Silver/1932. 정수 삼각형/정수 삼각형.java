import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        int[][] triangle = new int[N][N];
        int[][] DpTable = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int j = 0;
            while(st.hasMoreTokens()){
                triangle[i][j++] = Integer.parseInt(st.nextToken());
            }
        }

        int max = triangle[0][0];
        DpTable[0][0] = triangle[0][0];
        for(int i = 1; i < N; i++){
            for(int j = 0; j < N; j++){
                int left = 0;
                int right = DpTable[i-1][j];
                if(j != 0){
                    left = DpTable[i-1][j-1];
                }

                DpTable[i][j] = Math.max(left, right) + triangle[i][j];
                max = Math.max(max,DpTable[i][j]);
            }
        }

        System.out.println(max);
    }
}