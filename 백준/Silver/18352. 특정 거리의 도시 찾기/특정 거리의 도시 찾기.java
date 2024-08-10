import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        List<List<Integer>> map = new ArrayList<>();
        Queue<Integer[]> queue = new ArrayDeque<>();
        List<Integer> answer = new ArrayList<>();
        boolean[] visited = new boolean[n+1];

        for(int i = 0; i <= n; i++){
            map.add(new ArrayList<Integer>());
        }
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map.get(a).add(b);
        }
        
        visited[x] = true;
        queue.add(new Integer[]{x,0});
        
        while(!queue.isEmpty()) {
            Integer[] node = queue.poll();

            int cityNum = node[0];
            int dist = node[1];
            if(dist == k){
                answer.add(cityNum);
            }else if(dist > k){
                break;
            }

            for(int linked : map.get(cityNum)){
                if(!visited[linked]){
                    queue.add(new Integer[]{linked, dist+1});
                    visited[linked] = true;
                }
            }
        }

        if(answer.size()==0){
            System.out.println(-1);
        }else{
            Collections.sort(answer);
            for(int i = 0; i < answer.size(); i++){
                System.out.println(answer.get(i));
            }
        }

    }
}
