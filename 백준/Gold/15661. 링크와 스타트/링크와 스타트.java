import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[][] status;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        status = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                status[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        generateCombination(N);

        System.out.println(min);
    }

    static void generateCombination(int N){
        for(int i = 0; i < (1<<N); i++){

            List<Integer> startTeam = new ArrayList<>();
            List<Integer> linkTeam = new ArrayList<>();

            int startStatusSum = 0;
            int linkStatusSum = 0;

            for(int j = 0; j < N; j++){
                if((i & (1<<j)) != 0){
                    startTeam.add(j);
                } else{
                    linkTeam.add(j);
                }
            }

            if(startTeam.isEmpty() || linkTeam.isEmpty()) continue;

            for(int j = 0; j < startTeam.size(); j++){
                for(int k = j + 1; k < startTeam.size(); k++){
                    startStatusSum += status[startTeam.get(j)][startTeam.get(k)];
                    startStatusSum += status[startTeam.get(k)][startTeam.get(j)];
                }
            }

            for(int j = 0; j < linkTeam.size(); j++){
                for(int k = j + 1; k < linkTeam.size(); k++){
                    linkStatusSum += status[linkTeam.get(j)][linkTeam.get(k)];
                    linkStatusSum += status[linkTeam.get(k)][linkTeam.get(j)];
                }
            }

            min = Math.min(min, Math.abs(startStatusSum - linkStatusSum));
        }
    }
}