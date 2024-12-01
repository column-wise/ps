import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        List<Integer> curRestingPlaces = new ArrayList<>();
        curRestingPlaces.add(0);
        curRestingPlaces.add(l);

        if(n != 0) {
            st = new StringTokenizer(br.readLine());

            for(int i = 0; i < n; i++) {
                curRestingPlaces.add(Integer.parseInt(st.nextToken()));
            }
        }

        Collections.sort(curRestingPlaces);

        int left = 1;
        int right = curRestingPlaces.get(curRestingPlaces.size() - 1);

        while(left <= right) {
            int cur = 0;
            PriorityQueue<Integer> pq = new PriorityQueue<>();

            for(int i = 0; i <= n; i++) {
                pq.add(-(curRestingPlaces.get(i+1) - curRestingPlaces.get(i)));
            }

            int mid = (left + right) / 2;

            for(int i = 0; i < m; i++) {
                int max = -pq.poll();
                pq.add(-(max - mid));
                pq.add(-mid);
            }

            if(-pq.poll() <= mid) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(left);
    }
}