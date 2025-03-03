import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((x,y) -> y-x);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for(int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if(maxHeap.isEmpty()) {
                maxHeap.add(num);
            } else if(maxHeap.peek() >= num) {
                maxHeap.add(num);
            } else if(maxHeap.peek() < num) {
                minHeap.add(num);
            }

            if(maxHeap.size() < minHeap.size()) {
                maxHeap.add(minHeap.poll());
            } else if(maxHeap.size() > minHeap.size() + 1) {
                minHeap.add(maxHeap.poll());
            }

            sb.append(maxHeap.peek()).append("\n");
        }

        System.out.println(sb);
    }
}