import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");

        // the number of song in the album
        int N = Integer.parseInt(inputs[0]);

        // song Length
        int L = Integer.parseInt(inputs[1]);

        // bell ringing cycle
        int D = Integer.parseInt(inputs[2]);

        int albumEndTime = (L + 5) * N - 5;
        int period = L + 5;
        boolean isBellRing = false;
        boolean isSongEnded = false;
        boolean canResponse = false;
        int t;
        for (t = 0; t < albumEndTime; t++) {

            if (t % period == L) {
                isSongEnded = true;
            }

            if (t % period == 0) {
                isSongEnded = false;
            }

            if (t % D == 0) {
                isBellRing = true;
            }
            if (t % D == 1) {
                isBellRing = false;
            }

            if (isBellRing && isSongEnded) {
                canResponse = true;
                break;
            }
        }

        if (!canResponse) {
            for (t = albumEndTime; ; t++) {
                if (t % D == 0) {
                    break;
                }
            }
        }

        System.out.println(t);
    }
}