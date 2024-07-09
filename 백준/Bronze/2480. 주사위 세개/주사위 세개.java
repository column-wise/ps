import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");

        int[] dice = new int[3];
        for (int i = 0; i < 3; i++) {
            dice[i] = Integer.parseInt(inputs[i]);
        }

        int result = 0;

        // 모든 숫자가 같은 경우
        if (dice[0] == dice[1] && dice[1] == dice[2]) {
            result = 10000 + dice[0] * 1000;
        }
        // 두 숫자가 같은 경우
        else if (dice[0] == dice[1] || dice[0] == dice[2]) {
            result = 1000 + dice[0] * 100;
        }
        else if (dice[1] == dice[2]) {
            result = 1000 + dice[1] * 100;
        }
        // 모두 다른 경우
        else {
            result = Math.max(dice[0], Math.max(dice[1], dice[2])) * 100;
        }

        System.out.println(result);
    }
}
