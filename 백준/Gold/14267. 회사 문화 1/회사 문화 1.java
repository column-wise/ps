import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<Employee> hierarchy;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        hierarchy = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            hierarchy.add(new Employee(i));
        }

        st = new StringTokenizer(br.readLine());

        // 입력되는 정보는 사원의 상사가 누구인가 이지만
        // 상사가 데리고 있는 부하 정보를 저장하는게 편할 거 같음
        for(int i = 0; i < n; i++) {
            int boss = Integer.parseInt(st.nextToken());
            if(boss != -1) {
                boss--;
                hierarchy.get(boss).subordinates.add(hierarchy.get(i));
            }
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int employeeID = Integer.parseInt(st.nextToken())-1;
            int praiseValue = Integer.parseInt(st.nextToken());
            hierarchy.get(employeeID).praiseCnt += praiseValue;
        }

        praise(hierarchy.get(0), null);

        for(int i = 0; i < n ; i++) {
            System.out.print(hierarchy.get(i).praiseCnt+" ");
        }

    }

    public static void praise(Employee cur, Employee boss) {
        if(boss != null){
            cur.praiseCnt += boss.praiseCnt;
        }

        // subordinate: 부하직원
        List<Employee> subordinates = cur.subordinates;
        for(int i = 0; i < subordinates.size(); i++) {
            praise(subordinates.get(i), cur);
        }
    }

    public static class Employee{
        int id;
        int praiseCnt = 0;
        List<Employee> subordinates;

        public Employee(int id) {
            this.id = id;
            subordinates = new ArrayList<>();
        }

        @Override
        public String toString() {
            return "Employee [id=" + id + ", praiseCnt=" + praiseCnt + ", subordinates=" + subordinates + "]";
        }
    }
}