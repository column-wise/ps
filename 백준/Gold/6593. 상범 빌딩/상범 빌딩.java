import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    static int[] end = new int[3];
    static int L;
    static int R;
    static int C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        L = Integer.parseInt(inputs[0]);
        R = Integer.parseInt(inputs[1]);
        C = Integer.parseInt(inputs[2]);

        while(L != 0 && R != 0 && C != 0){
            int[] start = new int[3];
            char[][][] building = new char[L][R][C];
            boolean[][][] visited = new boolean[L][R][C];

            for(int i = 0; i < L; i ++){
                for(int j = 0; j < R; j++){
                    String line = br.readLine();
                    for(int k = 0; k < C; k++){
                        char n = line.charAt(k);
                        building[i][j][k] = n;
                        if(n == 'S'){
                            start[0] = i;
                            start[1] = j;
                            start[2] = k;
                        } else if (n == 'E'){
                            end[0] = i;
                            end[1] = j;
                            end[2] = k;
                        }
                    }
                }
                br.readLine();
            }

            int result = BFS(building, visited, start);
            if(result == -1){
                System.out.println("Trapped!");
            }else {
                System.out.println("Escaped in " + result + " minute(s).");
            }

            inputs = br.readLine().split(" ");
            L = Integer.parseInt(inputs[0]);
            R = Integer.parseInt(inputs[1]);
            C = Integer.parseInt(inputs[2]);
        }
    }

    public static int BFS(char[][][] building, boolean[][][] visited, int[] start){
        int[] dz = {1, -1, 0, 0, 0, 0};
        int[] dx = {0, 0, 1, -1, 0, 0};
        int[] dy = {0, 0, 0, 0, 1, -1};
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{start[0], start[1], start[2], 0});
        visited[start[0]][start[1]][start[2]] = true;

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int z = current[0];
            int x = current[1];
            int y = current[2];
            int count = current[3];

            if(z == end[0] && x == end[1] && y == end[2]){
                return count;
            }

            for(int i = 0; i < 6; i++){
                int nz = z + dz[i];
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nz >= 0 && nz < L && nx >= 0 && nx < R && ny >= 0 && ny < C &&
                        !visited[nz][nx][ny] && building[nz][nx][ny] != '#'){
                    visited[nz][nx][ny] = true;
                    queue.add(new int[]{nz, nx, ny, count + 1});
                }
            }
        }
        return -1;
    }
}
