import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int G, P;
    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());

        parents = new int[G+1];
        for (int i = 0; i <= G; i++) {
            parents[i] = i;
        }

        int count = 0;
        for(int i = 0; i < P; i++) {
            int gate = Integer.parseInt(br.readLine());

            int dockingGate = find(gate);

            if(dockingGate == 0) {
                break;
            }

            union(dockingGate, dockingGate - 1);
            count++;
        }

        System.out.println(count);
    }

    private static int find(int x) {
        if (parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }

    private static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        parents[rootA] = rootB;
    }
}