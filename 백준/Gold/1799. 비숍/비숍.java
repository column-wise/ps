import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int max;
    static List<Bishop> bishopsOnBlack;
    static List<Bishop> bishopsOnWhite;
    static LinkedList<Integer> chosen;
    static boolean[][] canPlaceOnBlack;
    static boolean[][] canPlaceOnWhite;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        bishopsOnBlack = new ArrayList<>();
        bishopsOnWhite = new ArrayList<>();


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    if ((i + j) % 2 == 0) {
                        bishopsOnBlack.add(new Bishop(i, j));
                    } else {
                        bishopsOnWhite.add(new Bishop(i, j));
                    }
                }
            }
        }

        canPlaceOnBlack = new boolean[bishopsOnBlack.size()][bishopsOnBlack.size()];
        canPlaceOnWhite= new boolean[bishopsOnWhite.size()][bishopsOnWhite.size()];

        for(int i = 0; i < bishopsOnBlack.size(); i++) {
            for(int j = i+1; j < bishopsOnBlack.size(); j++) {
                if(bishopsOnBlack.get(i).checkPosition(bishopsOnBlack.get(j))) {
                    canPlaceOnBlack[i][j] = true;
                    canPlaceOnBlack[j][i] = true;
                }
            }
        }

        for(int i = 0; i < bishopsOnWhite.size(); i++) {
            for(int j = i+1; j < bishopsOnWhite.size(); j++) {
                if(bishopsOnWhite.get(i).checkPosition(bishopsOnWhite.get(j))) {
                    canPlaceOnWhite[i][j] = true;
                    canPlaceOnWhite[j][i] = true;
                }
            }
        }

        chosen = new LinkedList<>();
        int blackMax = dfs(bishopsOnBlack, canPlaceOnBlack, 0, chosen);

        chosen = new LinkedList<>();
        int whiteMax = dfs(bishopsOnWhite, canPlaceOnWhite, 0, chosen);

        System.out.println(blackMax+whiteMax);
    }

    private static class Bishop {
        int x, y;

        private Bishop(int x, int y) {
            this.x = x;
            this.y = y;
        }

        private boolean checkPosition(Bishop b) {
            int sum = x+y;
            int diff = x-y;
            if(sum == b.x+b.y || diff == b.x-b.y) return false;
            return true;
        }
    }

    private static int dfs(List<Bishop> bishops, boolean[][] canPlace, int start, LinkedList<Integer> chosen) {
        int max = chosen.size();

        for(int i = start; i < bishops.size(); i++) {
            boolean canPlaceHere = true;
            for(int idx : chosen) {
                if(!canPlace[i][idx]) {
                    canPlaceHere = false;
                    break;
                }
            }

            if(canPlaceHere) {
                chosen.add(i);
                max = Math.max(max, dfs(bishops, canPlace, i+1, chosen));
                chosen.remove(chosen.size()-1);
            }
        }

        return max;
    }

}