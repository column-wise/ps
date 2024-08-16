import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception{
		int T = 10;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(int test_case = 1; test_case <= T; test_case++) {
			
			br.readLine();
			
			st = new StringTokenizer(br.readLine());
			Deque<Integer> deque = new ArrayDeque<>();
			for(int i = 0; i < 8; i++) {
				deque.add(Integer.parseInt(st.nextToken()));
			}
			
			int decrease = 1;
			
			while(true) {
				int n = deque.poll();
				n -= decrease++;
				if(decrease>5) {
					decrease = 1;
				}
				if(n <= 0) {
					deque.add(0);
					break;
				}else {
					deque.add(n);
				}
//				for(Integer elem:deque) {
//					System.out.print(elem+" ");
//				}
//				System.out.println();
			}
			
			System.out.print("#"+test_case+" ");
			for(int i = 0; i < 8; i++) {
				System.out.print(deque.poll()+" ");
			}
			System.out.println();
		}
	}
}