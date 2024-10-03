import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;

        while ((input = br.readLine()) != null) {
            try {
                boolean found = false;
                int X = Integer.parseInt(input);
                X *= 10000000;

                input = br.readLine();
                if (input == null) break;
                int n = Integer.parseInt(input);

                List<Integer> blocks = new ArrayList<>();

                for (int i = 0; i < n; i++) {
                    input = br.readLine();
                    if (input == null) break;
                    blocks.add(Integer.parseInt(input));
                }

                Collections.sort(blocks);

                int left = 0;
                int right = blocks.size() - 1;

                while (left < right) {
                    int l = blocks.get(left);
                    int r = blocks.get(right);
                    if (l + r == X) {
                        System.out.println("yes " + l + " " + r);
                        found = true;
                        break;
                    } else if (l + r < X) {
                        left++;
                    } else {
                        right--;
                    }
                }

                if (!found) {
                    System.out.println("danger");
                }
            } catch (NumberFormatException e) {
                return;
            }
        }
    }
}