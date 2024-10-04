import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        List<Integer> index = new ArrayList<>();
        int[] values = new int[501];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            index.add(idx);
            values[idx] = val;
        }

        Collections.sort(index);

        int len = 0;
        int[] lis = new int[N+1];

        for(int i = 0; i < N; i++){
            int idx = index.get(i);
            if(lis[len] < values[idx]){
                len++;
                lis[len] = values[idx];
            } else{
                int injectionIdx = -(Arrays.binarySearch(lis,0, len, values[idx])+1);
                lis[injectionIdx] = values[idx];
            }

        }

        System.out.println(N-len);

    }
}