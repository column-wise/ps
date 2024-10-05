import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        List<Integer> index = new ArrayList<>();
        Map<Integer, Integer> val2idx = new HashMap<>();
        int[] values = new int[500001];
        int[] prev = new int[500001];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            index.add(idx);
            values[idx] = val;
            val2idx.put(val,idx);
        }

        Collections.sort(index);

        int len = 0;
        int[] arr = new int[N+1];

        for(int i = 0; i < N; i++){
            int idx = index.get(i);
            if(arr[len] < values[idx]){
                len++;
                arr[len] = values[idx];
                prev[values[idx]] = arr[len - 1];
            } else{
                int injectionIdx = -(Arrays.binarySearch(arr,0, len, values[idx])+1);
                arr[injectionIdx] = values[idx];
                prev[values[idx]] = arr[injectionIdx-1];
            }
        }

        List<Integer> lis = new ArrayList<>();
        int l = arr[len];
        while(l != 0){
            lis.add(val2idx.get(l));
            l = prev[l];
        }

        System.out.println(N-len);


        int p1 = 0;
        int p2 = len-1;

        for(int i = 0; i < N; i++){
            if(p2 >= 0 && Objects.equals(index.get(p1), lis.get(p2))){
                p1++;
                p2--;
            } else {
                System.out.println(index.get(p1));
                p1++;
            }
        }
    }
}