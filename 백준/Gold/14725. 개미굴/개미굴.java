import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        Node root = new Node(0, "root");

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());

            String[] route = new String[l];
            for(int j = 0; j < l; j++) {
                route[j] = st.nextToken();
            }

            root.add(route, 0);
        }

        root.traverse();
    }

    private static class Node implements Comparable<Node> {
        int floor;
        String food;
        PriorityQueue<Node> pq;
        Map<String, Node> map;

        public Node(int floor, String food) {
            this.floor = floor;
            this.food = food;
            map = new HashMap<>();
            pq = new PriorityQueue<>();
        }

        public void add(String[] foods, int floor) {
            if(floor >= foods.length) return;

            if(!map.containsKey(foods[floor])) {
                Node newNode = new Node(floor, foods[floor]);
                pq.add(newNode);
                map.put(foods[floor], newNode);
            }

            map.get(foods[floor]).add(foods, floor + 1);
        }

        @Override
        public int compareTo(Node o) {
            return food.compareTo(o.food);
        }

        private void traverse() {
            for(int i = 0; i < floor; i++) {
                System.out.print("--");
            }

            if(!food.equals("root")) {
                System.out.println(food);
            }

            while(!pq.isEmpty()) {
                pq.poll().traverse();
            }
        }
    }
}