import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++){
            int k = Integer.parseInt(br.readLine());
            PriorityQueue<Node> minHeap = new PriorityQueue<>(new minHeapComparator());
            PriorityQueue<Node> maxHeap = new PriorityQueue<>(new maxHeapComparator());

            int count = 0;
            for(int i = 0; i < k; i++){
                st = new StringTokenizer(br.readLine());
                String command = st.nextToken();
                int value = Integer.parseInt(st.nextToken());

                if(command.equals("I")){
                    Node newNode = new Node(value);
                    minHeap.add(newNode);
                    maxHeap.add(newNode);
                    count ++;
                } else{
                    if(count == 0) continue;
                    Node pop = null;
                    if(value == 1){
                        while(!maxHeap.isEmpty()){
                            pop = maxHeap.poll();
                            if(pop.isValid) break;
                        }
                    } else{
                        while(!minHeap.isEmpty()){
                            pop = minHeap.poll();
                            if(pop.isValid) break;
                        }
                    }
                    pop.isValid = false;
                    count --;
                }
            }

            if(count != 0){
                Node max = null;
                Node min = null;
                while(!maxHeap.isEmpty()){
                    max = maxHeap.poll();
                    if(max.isValid) break;
                }

                while(!minHeap.isEmpty()){
                    min = minHeap.poll();
                    if(min.isValid) break;
                }

                System.out.print(max.value+" ");
                System.out.println(min.value);
            } else {
                System.out.println("EMPTY");
            }
        }
    }

    private static class minHeapComparator implements Comparator<Node>{

        @Override
        public int compare(Node o1, Node o2) {
            return Integer.compare(o1.value, o2.value);
        }
    }

    private static class maxHeapComparator implements Comparator<Node>{

        @Override
        public int compare(Node o1, Node o2) {
            return Integer.compare(o2.value, o1.value);
        }
    }

    private static class Node{
        int value;
        boolean isValid;

        private Node(int value){
            this.value = value;
            isValid = true;
        }
    }

}