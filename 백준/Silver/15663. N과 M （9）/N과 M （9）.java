import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int[] arr;
    static boolean[] isUsed;
    static StringBuilder sb;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        isUsed = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        backtracking(new ArrayList<>());
        System.out.println(sb);
    }
    private static void backtracking(List<Integer> seq){
        if(seq.size() == M){
            for(int n : seq){
                sb.append(n).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i = 0; i < N; i++){

            // isUsed[i]: 원소 중복 사용을 막기 위함
            // (i>0 && arr[i] == arr[i-1] && !isUsed[i-1]): 이미 생성된 수열 조합을 방지하기 위함
            // arr가 정렬되어 있기 때문에 arr[i] == arr[i-1] && !isUsed[i-1]을 만족한다면 이미 이전에 수열이 생성되었음.
            if(isUsed[i] || (i>0 && arr[i] == arr[i-1] && !isUsed[i-1])) continue;

            seq.add(arr[i]);
            isUsed[i] = true;
            backtracking(seq);
            seq.remove(seq.size()-1);
            isUsed[i] = false;
        }
    }
}