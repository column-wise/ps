import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<Integer>[] adjList;
    static Node[] nodes;
    static int[][] DpTable;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        adjList = new ArrayList[n];
        nodes = new Node[n];

        for(int i = 0; i < n; i++){
            adjList[i] = new ArrayList<>();
            nodes[i] = new Node(i);
        }

        DpTable = new int[n][2];

        for(int i = 0; i < n-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            adjList[a].add(b);
            adjList[b].add(a);

            nodes[a].neighbors.add(b);
            nodes[b].neighbors.add(a);
        }

        fillDpTable(nodes[0], null);

        System.out.println(Math.min(DpTable[0][0], DpTable[0][1]));

    }

    static class Node{
        int idx;
        List<Integer> neighbors;

        public Node(int idx){
            this.idx = idx;
            neighbors = new ArrayList<>();
        }

        @Override
        public String toString() {
            return "Node{" +
                    "idx=" + idx +
                    '}';
        }
    }

    static void fillDpTable(Node cur, Node parent){
        int min = 0;

        for(int childIdx : cur.neighbors){
            Node childNode = nodes[childIdx];
            if(childNode != parent){
                fillDpTable(childNode, cur);

                // 0: 선택 x / 1: 선택 o
                DpTable[cur.idx][0] += DpTable[childIdx][1];
                min += Math.min(DpTable[childIdx][0], DpTable[childIdx][1]);
            }
        }
        DpTable[cur.idx][1] = min + 1;
    }
}