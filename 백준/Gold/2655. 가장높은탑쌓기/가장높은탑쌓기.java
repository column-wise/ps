import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Brick[] bricks = new Brick[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int area = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            bricks[i] = new Brick(i+1, area, height, weight);
        }

        Arrays.sort(bricks);

        int[] dp = new int[N];
        int[] prev = new int[N];

        for (int i = 0; i < N; i++) {
            dp[i] = bricks[i].height;
            prev[i] = -1;
        }

        int maxHeight = 0;
        int maxIndex = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (bricks[j].weight < bricks[i].weight) {
                    if (dp[j] + bricks[i].height > dp[i]) {
                        dp[i] = dp[j] + bricks[i].height;
                        prev[i] = j;
                    }
                }
            }
            if (dp[i] > maxHeight) {
                maxHeight = dp[i];
                maxIndex = i;
            }
        }

        StringBuilder sb = new StringBuilder();

        int[] stack = new int[N];
        int count = 0;
        int top = 0;
        while (maxIndex != -1) {
            stack[top++] = bricks[maxIndex].idx;
            maxIndex = prev[maxIndex];
        }

        for (int i = top-1; i >= 0; i--) {
            sb.append(stack[i]).append("\n");
            count++;
        }

        System.out.println(count);
        System.out.print(sb);
    }

    static class Brick implements Comparable<Brick> {
        int idx;
        int area;
        int height;
        int weight;

        public Brick(int idx, int area, int height, int weight) {
            this.idx = idx;
            this.area = area;
            this.height = height;
            this.weight = weight;
        }

        @Override
        public int compareTo(Brick o) {
            return this.area - o.area;
        }
    }
}