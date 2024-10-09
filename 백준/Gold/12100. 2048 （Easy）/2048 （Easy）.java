import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] initialMap;
    static int max;
    static List<List<Integer>> permutations;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        initialMap = new int[N][N];
        max = 0;
        permutations = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                initialMap[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, initialMap[i][j]);
            }
        }

        permutation(new ArrayList<>());
        int[][] map = new int[N][N];

        for(List<Integer> l : permutations){
            for(int i = 0; i < N; i++){
                map[i] = initialMap[i].clone();
            }

            for(int direction : l){
                slide(map, direction);
            }
        }

        System.out.println(max);
    }

    private static void permutation(List<Integer> numCase){
        if(numCase.size() == 5){
            permutations.add(new ArrayList<>(numCase));
            return;
        }

        for(int i = 0; i < 4; i++){
            numCase.add(i);
            permutation(numCase);
            numCase.remove(numCase.size()-1);
        }
    }

    private static void slide(int[][] map, int direction) {
        compressTiles(map, direction);
        mergeTiles(map, direction);
        compressTiles(map, direction);
    }

    private static void compressTiles(int[][] map, int direction) {
        switch (direction) {
            case 0:
                for (int i = 0; i < N; i++) {
                    for (int j = N - 1; j > 0; j--) {
                        if (map[i][j] == 0) {
                            int count = 1;
                            while (j - count >= 0 && map[i][j - count] == 0) {
                                count++;
                            }

                            if (j - count < 0) break;

                            map[i][j] = map[i][j - count];
                            map[i][j - count] = 0;
                        }
                    }
                }
                break;
            case 1:
                for (int j = 0; j < N; j++) {
                    for (int i = N - 1; i > 0; i--) {
                        if (map[i][j] == 0) {
                            int count = 1;
                            while (i - count >= 0 && map[i - count][j] == 0) {
                                count++;
                            }

                            if (i - count < 0) break;

                            map[i][j] = map[i - count][j];
                            map[i - count][j] = 0;
                        }
                    }
                }
                break;
            case 2:
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (map[i][j] == 0) {
                            int count = 1;
                            while (j + count < N && map[i][j + count] == 0) {
                                count++;
                            }

                            if (j + count >= N) break;

                            map[i][j] = map[i][j + count];
                            map[i][j + count] = 0;
                        }
                    }
                }
                break;
            case 3:
                for (int j = 0; j < N; j++) {
                    for (int i = 0; i < N; i++) {
                        if (map[i][j] == 0) {
                            int count = 1;
                            while (i + count < N && map[i + count][j] == 0) {
                                count++;
                            }

                            if (i + count >= N) break;

                            map[i][j] = map[i + count][j];
                            map[i + count][j] = 0;
                        }
                    }
                }
                break;
        }
    }

    private static void mergeTiles(int[][] map, int direction) {
        switch (direction) {
            case 0:
                for (int i = 0; i < N; i++) {
                    for (int j = N - 1; j > 0; j--) {
                        if (map[i][j] == map[i][j - 1]) {
                            map[i][j] *= 2;
                            max = Math.max(max, map[i][j]);
                            map[i][j - 1] = 0;
                        }
                    }
                }
                break;
            case 1:
                for (int j = 0; j < N; j++) {
                    for (int i = N - 1; i > 0; i--) {
                        if (map[i][j] == map[i - 1][j]) {
                            map[i][j] *= 2;
                            max = Math.max(max, map[i][j]);
                            map[i - 1][j] = 0;
                        }
                    }
                }
                break;
            case 2:
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N - 1; j++) {
                        if (map[i][j] == map[i][j + 1]) {
                            map[i][j] *= 2;
                            max = Math.max(max, map[i][j]);
                            map[i][j + 1] = 0;
                        }
                    }
                }
                break;
            case 3:
                for (int j = 0; j < N; j++) {
                    for (int i = 0; i < N - 1; i++) {
                        if (map[i][j] == map[i + 1][j]) {
                            map[i][j] *= 2;
                            max = Math.max(max, map[i][j]);
                            map[i + 1][j] = 0;
                        }
                    }
                }
                break;
        }
    }
}