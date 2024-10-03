import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        List<Magnet> magnets = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            String[] inputs = br.readLine().split("");
            Magnet newMagnet = new Magnet();

            MagnetBlade prevBlade = null;
            MagnetBlade newBlade = null;

            for (int j = 0; j < 8; j++) {
                prevBlade = newBlade;
                newBlade = new MagnetBlade(Integer.parseInt(inputs[j]));

                newBlade.prev = prevBlade;
                if (prevBlade != null) {
                    prevBlade.next = newBlade;
                }

                if (j == 0) {
                    newMagnet.redArrow = newBlade;
                }

                if (j == 2) {
                    newMagnet.rightMagnetSurface = newBlade;
                }

                if (j == 6) {
                    newMagnet.leftMagnetSurface = newBlade;
                }

                if (j == 7) {
                    newBlade.next = newMagnet.redArrow;
                    newMagnet.redArrow.prev = newBlade;
                }
            }

            magnets.add(newMagnet);
        }

        int K = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int targetMag = Integer.parseInt(st.nextToken()) - 1;
            int rotationDirection = Integer.parseInt(st.nextToken());

            Magnet m = magnets.get(targetMag);
            boolean[] isDifferentPole = new boolean[3];

            for (int j = 0; j < 3; j++) {
                Magnet left = magnets.get(j);
                Magnet right = magnets.get(j + 1);

                if (left.rightMagnetSurface.magnetPole != right.leftMagnetSurface.magnetPole) {
                    isDifferentPole[j] = true;
                }
            }

            for (int j = targetMag - 1; j >= 0; j--) {
                if (isDifferentPole[j]) {
                    int direction = rotationDirection;
                    for (int k = 0; k < targetMag - j; k++) {
                        direction = -1 * direction;
                    }
                    magnets.get(j).rotate(direction);
                } else {
                    break;
                }
            }

            for (int j = targetMag + 1; j < 4; j++) {
                if (isDifferentPole[j - 1]) {
                    int direction = rotationDirection;
                    for (int k = 0; k < j - targetMag; k++) {
                        direction = -1 * direction;
                    }
                    magnets.get(j).rotate(direction);
                } else {
                    break;
                }
            }

            m.rotate(rotationDirection);

        }

        int result = 0;
        for (int i = 0; i < 4; i++) {
            result += magnets.get(i).redArrow.magnetPole * (int) Math.pow(2, i);
        }
        System.out.println(result);

    }

    private static class MagnetBlade {
        int magnetPole;
        MagnetBlade prev;
        MagnetBlade next;

        public MagnetBlade(int magnetPole) {
            super();
            this.magnetPole = magnetPole;
        }
    }

    private static class Magnet {
        MagnetBlade redArrow;
        MagnetBlade leftMagnetSurface;
        MagnetBlade rightMagnetSurface;

        private void rotate(int direction) {
            if (direction == 1) {
                redArrow = redArrow.prev;
                leftMagnetSurface = leftMagnetSurface.prev;
                rightMagnetSurface = rightMagnetSurface.prev;
            } else {
                redArrow = redArrow.next;
                leftMagnetSurface = leftMagnetSurface.next;
                rightMagnetSurface = rightMagnetSurface.next;
            }
        }
    }
}