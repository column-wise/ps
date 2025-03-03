import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static TreeSet<Node> nodes;
    static HashMap<Integer, Node> map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        nodes = new TreeSet<>();
        map = new HashMap<>();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            Node n = new Node(P, L);
            nodes.add(n);
            map.put(P, n);
        }

        int M = Integer.parseInt(br.readLine());
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            if(cmd.equals("recommend")) {
                int x = Integer.parseInt(st.nextToken());
                if(x == 1) {
                    sb.append(nodes.first().num).append('\n');
                } else if(x == -1) {
                    sb.append(nodes.last().num).append('\n');
                }
            } else if(cmd.equals("add")) {
                int P = Integer.parseInt(st.nextToken());
                int L = Integer.parseInt(st.nextToken());
                nodes.add(new Node(P, L));
            } else if(cmd.equals("solved")) {
                int P = Integer.parseInt(st.nextToken());
                Node n = map.get(P);
                if(n != null) {
                    nodes.remove(n);
                }
            }
        }

        System.out.println(sb);
    }

    private static class Node implements Comparable<Node>{
        int num;
        int level;

        public Node (int num, int level) {
            this.num = num;
            this.level = level;
        }

        public int compareTo(Node o) {
            int ret = o.level - this.level;
            if(ret == 0) {
                ret = o.num - this.num;
            }
            return ret;
        }

        public String toString() {
            return num + " " + level;
        }
    }
}