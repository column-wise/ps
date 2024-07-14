import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Boolean [][] paper = new Boolean[100][100];

        for(int i = 0; i < 100; i++){
            for(int j = 0; j < 100; j++){
                paper[i][j] = false;
            }
        }

        int N = Integer.parseInt(reader.readLine());

        for(int i = 0; i < N; i++){
            String[] inputs = reader.readLine().split(" ");
            int X = Integer.parseInt(inputs[0]);
            int Y = Integer.parseInt(inputs[1]);

            for(int x = X; x<X+10; x++){
                for(int y = Y; y<Y+10; y++){
                    paper[x][y] = true;
                }
            }
        }
        int count = 0;
        for(int i = 0; i < 100; i ++){
            for(int j = 0; j < 100; j++){
                if(paper[i][j]){
                    count += 1;
                }
            }
        }
        System.out.println(count);
    }
}
