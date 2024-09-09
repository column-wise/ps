import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<boolean[]> comb;
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] population = new int[n];
        List<Integer>[] adjList = new ArrayList[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            population[i] = Integer.parseInt(st.nextToken());
            adjList[i] = new ArrayList<>();
        }

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            while(st.hasMoreTokens()){
                adjList[i].add(Integer.parseInt(st.nextToken()) - 1);
            }
        }

        int min = INF;
        comb = new ArrayList<>();
        generateComb(n);

        for(boolean[] districtA  : comb){
            boolean[] districtB = new boolean[n];
            for(int i = 0; i < n; i++){
                districtB[i] = !districtA[i];
            }

            Deque<Integer> queue = new ArrayDeque<>();
            int peopleA = 0;
            for(int i = 0; i < n; i++){
                if(!districtA[i]){
                    queue.add(i);
                    districtA[i] = true;
                    break;
                }
            }

            while(!queue.isEmpty()){
                int cur = queue.poll();
                peopleA += population[cur];

                for(int neighbor : adjList[cur]){
                    if(!districtA[neighbor]){
                        queue.add(neighbor);
                        districtA[neighbor] = true;
                    }
                }
            }

            boolean isConnectedA = true;
            for(int i = 0; i < n; i++){
                if(!districtA[i]){
                    isConnectedA = false;
                    break;
                }
            }

            if(!isConnectedA){
                continue;
            }

            queue = new ArrayDeque<>();
            int peopleB = 0;
            for(int i = 0; i < n; i++){
                if(!districtB[i]){
                    queue.add(i);
                    districtB[i] = true;
                    break;
                }
            }

            while(!queue.isEmpty()){
                int cur = queue.poll();
                peopleB += population[cur];

                for(int neighbor : adjList[cur]){
                    if(!districtB[neighbor]){
                        queue.add(neighbor);
                        districtB[neighbor] = true;
                    }
                }
            }

            boolean isConnectedB = true;
            for(int i = 0; i < n; i++){
                if(!districtB[i]){
                    isConnectedB = false;
                    break;
                }
            }

            if(isConnectedB){
                min = Math.min(min, Math.abs(peopleA - peopleB));
            }
        }

        System.out.println(min!=INF?min:-1);
    }

    static void generateComb(int n){
        for(int i = 1; i < (1<<n) - 1; i++){
            boolean[] combination = new boolean[n];
            for(int j = 0; j < n; j++){
                if((i&(1<<j)) != 0){
                    combination[j] = true;
                }
            }
            comb.add(combination);
        }
    }
}