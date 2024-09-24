import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        List<Homework> list = new ArrayList<>();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int deadline = Integer.parseInt(st.nextToken());
            int reward = Integer.parseInt(st.nextToken());

            list.add(new Homework(deadline, reward));
        }

        Collections.sort(list);
        int max = 0;

        for(int i = 0; i < N; i++){
            Homework hw = list.get(i);
            pq.add(hw.reward);

            if(pq.size() > hw.deadline){
                pq.poll();
            }
        }

        while(!pq.isEmpty()){
            max += pq.poll();
        }

        System.out.println(max);

    }

    static class Homework implements Comparable<Homework>{
        int deadline;
        int reward;

        private Homework(int deadline, int reward){
            this.deadline = deadline;
            this.reward = reward;
        }

        @Override
        public int compareTo(Homework o) {
            return Integer.compare(deadline, o.deadline);
        }
    }
}