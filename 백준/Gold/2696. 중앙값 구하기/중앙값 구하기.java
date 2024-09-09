import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++) {
            int n = Integer.parseInt(br.readLine());
            int count = (int) Math.round(n/2.0);

            Node head = null;
            Node tail = null;
            Node median = null;
            double move = 0.0;
            int printCnt = 0;

            for(int i = 0; i <= n/10; i++){
                st = new StringTokenizer(br.readLine());

                if(head == null){
                    System.out.println(count);
                    head = new Node(Integer.parseInt(st.nextToken()));
                    tail = head;
                    median = head;

                    head.next = median;
                    median.prev = head;
                    median.next = tail;
                    tail.prev = median;

                    System.out.print(median.val+" ");
                    move = 0.0;
                    printCnt ++;
                }

                while(st.hasMoreTokens()){
                    int val = Integer.parseInt(st.nextToken());

                    if(val <= head.val){
                        Node temp = new Node(val);
                        temp.next = head;
                        head.prev = temp;
                        head = temp;
                    } else if(val > tail.val){
                        Node temp = new Node(val);
                        tail.next = temp;
                        temp.prev = tail;
                        tail = temp;
                    } else {
                        Node ref = head;
                        while (ref != null && val > ref.val) {
                            ref = ref.next;
                        }
                        Node temp = new Node(val);
                        ref.prev.next = temp;
                        temp.prev = ref.prev;
                        temp.next = ref;
                        ref.prev = temp;
                    }

                    if(val <= median.val){
                        move -= 0.5;
                    } else{
                        move += 0.5;
                    }

                    if(move % 1 != 0.5 && move % 1 != -0.5) {
                        if (move == 1.0) {
                            median = median.next;
                        } else if (move == -1.0) {
                            median = median.prev;
                        }
                        move = 0.0;
                        System.out.print(median.val+" ");
                        printCnt ++;
                    }

                }
            }
            System.out.println();
        }
    }

    private static class Node{
        int val;
        Node prev;
        Node next;

        private Node(int val){
            this.val = val;
            prev = null;
            next = null;
        }
    }
}