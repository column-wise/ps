import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 0; test_case < T; test_case++) {
            int n = Integer.parseInt(br.readLine());

            // 정렬을 하기가 싫더라구요
            // 기존에 이차원 배열로 구현했는데 그냥 리스트 쓰면 더 빠른데 왜 이차원 배열 썼나 싶어서 고쳤습니다.
            int[] appliances = new int[n];
            StringTokenizer st;
            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken())-1;
                int b = Integer.parseInt(st.nextToken())-1;

                appliances[a] = b;
            }

            // 서류시험 1등의 면접 점수를 최소값으로 설정하고
            // 서류시험 등수가 최소값보다 크면
            // 서류도 못보고 면접도 못본거니까 count를 안하는 방식으로 접근했습니다.
            int min = appliances[0];
            int count = 1;
            for(int i = 1; i < n; i++) {
                int temp = appliances[i];
                if(temp<min) {
                    min = temp;
                    count ++;
                }
            }

            System.out.println(count);

        }
    }
}
