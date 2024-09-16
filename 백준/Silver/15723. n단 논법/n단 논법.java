import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int n = Integer.parseInt(br.readLine());
        List<List<Integer>> directChild = new ArrayList<>();

        for(int i = 0; i < 26; i++){
            directChild.add(new ArrayList<>());
        }

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int from = st.nextToken().charAt(0) - 'a';
            st.nextToken();
            int to = st.nextToken().charAt(0) - 'a';

            directChild.get(from).add(to);
        }

        int m = Integer.parseInt(br.readLine());

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int from = st.nextToken().charAt(0) - 'a';
            st.nextToken();
            int to = st.nextToken().charAt(0) - 'a';

            Deque<Integer> queue = new ArrayDeque<>();
            boolean[] visited = new boolean[26];
            queue.add(from);
            visited[from] = true;
            boolean isRight = false;

            while(!queue.isEmpty()){
                int cur = queue.poll();
                if(cur == to){
                    isRight = true;
                    break;
                }

                for(int child : directChild.get(cur)){
                    if(!visited[child]){
                        visited[child] = true;
                        queue.add(child);
                    }
                }
            }

            if(isRight){
                System.out.println("T");
            } else{
                System.out.println("F");
            }
        }

    }
}