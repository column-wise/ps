import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if(N == K) {
            System.out.println(0);
            System.out.println(1);
            return;
        }

        int[][] dp = new int[100001][2];
        for(int i = 0; i < 100001; i++) {
            dp[i][0] = -1;
        }

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(N, 0));

        while(!queue.isEmpty()) {
            Node cur = queue.poll();
            int x = cur.x;
            int time = cur.time;

            for(int i = 0; i < 3; i++) {
                int next = getNextX(x, i);

                if(next < 0 || next > 100000) continue;

                if(dp[next][0] == -1 || dp[next][0] > time + 1) {
                    dp[next][0] = time + 1;
                    dp[next][1] = 1;
                    queue.add(new Node(next, time+1));
                } else if(dp[next][0] == time + 1) {
                    dp[next][1] ++;
                    queue.add(new Node(next, time+1));
                }
            }
        }

        System.out.println(dp[K][0]);
        System.out.println(dp[K][1]);
    }

    private static class Node {
        int x;
        int time;

        public Node(int x, int time) {
            this.x = x;
            this.time = time;
        }
    }

    private static int getNextX (int x, int option) {
        if(option == 0) {
            return x + 1;
        } else if(option == 1) {
            return x - 1;
        } else{
            return x * 2;
        }
    }
}