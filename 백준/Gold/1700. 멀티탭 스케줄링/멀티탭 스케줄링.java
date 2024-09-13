import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] multitab = new int[n];
        int count = 0;

        st = new StringTokenizer(br.readLine());
        int[] inputs = new int[m];

        for(int i = 0; i < m; i++){
            inputs[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < m; i++){

            boolean haveToPop = true;

            for(int j = 0; j < n; j++){
                if(multitab[j] == inputs[i]){
                    haveToPop = false;
                    break;
                }
            }

            if(haveToPop){
                for(int j = 0; j < n; j++){
                    if(multitab[j] == 0){
                        multitab[j] = inputs[i];
                        haveToPop = false;
                        break;
                    }
                }
            }

            if(haveToPop){
                int[] next = new int[n];
                Arrays.fill(next, 101);

                for(int j = m-1; j > i; j--){
                    for(int k = 0; k < n; k++){
                        if(multitab[k] == inputs[j]){
                            next[k] = j;
                        }
                    }
                }

                int popIdx = -1;
                int max = 0;
                for(int j = 0; j < n; j++){
                    if(next[j] > max){
                        max = next[j];
                        popIdx = j;
                    }
                }

                multitab[popIdx] = inputs[i];
                count ++;
            }
        }

        System.out.println(count);

    }
}