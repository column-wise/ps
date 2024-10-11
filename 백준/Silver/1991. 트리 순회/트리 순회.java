import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Node[] nodes;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        nodes = new Node[N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int root = (st.nextToken().charAt(0) - 'A');
            int left = (st.nextToken().charAt(0) - 'A');
            int right = (st.nextToken().charAt(0) - 'A');

            nodes[root] = new Node(left, right);
        }

        preorder(0);
        sb.append("\n");
        inorder(0);
        sb.append("\n");
        postorder(0);
        sb.append("\n");

        System.out.println(sb);
    }
    private static class Node{
        int left;
        int right;

        public Node(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    private static void preorder(int root) {
        sb.append((char)(root+'A'));
        Node cur = nodes[root];
        if(cur.left>=0){
            preorder(cur.left);
        }
        if(cur.right>=0){
            preorder(cur.right);
        }
    }
    private static void inorder(int root) {
        Node cur = nodes[root];
        if(cur.left>=0){
            inorder(cur.left);
        }
        sb.append((char)(root+'A'));
        if(cur.right>=0){
            inorder(cur.right);
        }
    }
    private static void postorder(int root) {
        Node cur = nodes[root];
        if(cur.left>=0){
            postorder(cur.left);
        }
        if(cur.right>=0){
            postorder(cur.right);
        }
        sb.append((char)(root+'A'));
    }
}