import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] result = new int[N];
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        for(int i = 0; i < N; i++){
            int val = Integer.parseInt(st.nextToken());
            map.putIfAbsent(val, new ArrayList<>());
            map.get(val).add(i);
        }

        int i = 0;
        while(!map.isEmpty()){
            Map.Entry<Integer, List<Integer>> entry = map.pollFirstEntry();
            for(int idx : entry.getValue()){
                result[idx] = i;
            }
            i++;
        }


        for(int compression : result){
            sb.append(compression).append(" ");
        }
        System.out.println(sb);
    }
}