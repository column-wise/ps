import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.exit;

public class Main {
    static int N;
    static int H;
    static Node[][] ladders;
    static boolean[][] linked;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        ladders = new Node[H+1][N];
        linked = new boolean[H+1][N];
        for(int i = 0; i <= H; i++) {
            for(int j = 0; j < N; j++) {
                ladders[i][j] = new Node(i, j);
            }
        }

        for(int i = 0; i < H; i++) {
            for(int j = 0; j < N; j++) {
                ladders[i][j].next = ladders[i+1][j];
            }
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            // zero-base index
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            link(a,b);
        }

        for(int i = 0; i < 4; i++) {
            choice(0, i, 0);
        }
        System.out.println(-1);
    }

    private static class Node {
        int x;
        int y;
        Node next;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void link(int a, int b) {
        if(b+1 == N) return;

        ladders[a][b].next = ladders[a+1][b+1];
        ladders[a][b+1].next = ladders[a+1][b];
        linked[a][b] = true;
    }

    private static void unlink(int a, int b) {
        if(b+1 == N) return;

        ladders[a][b].next = ladders[a+1][b];
        ladders[a][b+1].next = ladders[a+1][b+1];
        linked[a][b] = false;
    }

    private static void choice(int depth, int target, int start) {
        if(depth == target) {
            if(check()) {
                System.out.println(target);
                exit(0);
            }
            return;
        }

        for(int i = start; i < N*H; i++) {
            if(linked[i/N][i%N]) continue;
            if(i%N > 0 && linked[i/N][i%N-1]) continue;
            if(i%N < N-1 && linked[i/N][i%N+1]) continue;
            link(i/N, i%N);
            choice(depth+1, target, i+1);
            unlink(i/N, i%N);
        }
    }

    private static boolean check() {
        for(int i = 0; i < N; i++) {
            Node cur = ladders[0][i];

            while(cur.next != null) {
                cur = cur.next;
            }

            if(cur.y != i) {
                return false;
            }
        }
        return true;
    }
}