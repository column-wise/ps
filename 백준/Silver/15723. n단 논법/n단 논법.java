import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int n = Integer.parseInt(br.readLine());
        
        // list directChild 에는 바로 자식만 담을거고
        // set childs에는 손자 손녀 다 담을 예정
        List<List<Integer>> directChild = new ArrayList<>();
        HashSet<Integer>[] childs = new HashSet[26];

        for(int i = 0; i < 26; i++){
            directChild.add(new ArrayList<>());
            childs[i] = new HashSet<Integer>();
            
            // a is a, z is z 처리를 위해 자기 자신은 미리 담아주었음
            childs[i].add(i);
        }

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int from = st.nextToken().charAt(0) - 'a';
            st.nextToken();
            int to = st.nextToken().charAt(0) - 'a';

            directChild.get(from).add(to);
        }

        for(int i = 0; i < 26; i++){
            Deque<Integer> queue = new ArrayDeque<>();
            boolean[] visited = new boolean[26];
            queue.add(i);
            visited[i] = true;

            while(!queue.isEmpty()){
                int cur = queue.poll();
                childs[i].add(cur);

                for(int child : directChild.get(cur)){
                    if(!visited[child]){
                        visited[child] = true;
                        queue.add(child);
                    }
                }
            }

        }

        int m = Integer.parseInt(br.readLine());

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int from = st.nextToken().charAt(0) - 'a';
            st.nextToken();
            int to = st.nextToken().charAt(0) - 'a';

            if(childs[from].contains(to)){
                System.out.println("T");
            } else{
                System.out.println("F");
            }
        }

    }
}