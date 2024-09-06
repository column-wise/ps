import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			String[] inputs = br.readLine().split("");
			
			int[] nums = new int[N+N/4];
			List<Integer> password;
			Set<Integer> duplicateCheck = new HashSet<>();
			
			for(int i = 0; i < N; i++) {
				nums[i] = Integer.decode("0x"+inputs[i]);
			}
			for(int i = 0; i < N/4; i++) {
				nums[N+i] = Integer.decode("0x"+inputs[i]);
			}
			
			int start = 0;
			int end = start + N/4 - 1;
			while(start <= N) {
				int sum = 0;
				for(int i = 0; i < N/4; i++) {
					sum += (int)Math.pow(16, i)*nums[end-i];
				}
				duplicateCheck.add(sum);
				start ++;
				end ++;
			}
			password = new ArrayList<> (duplicateCheck);
			Collections.sort(password, (x1,x2) -> x2-x1);
			System.out.println("#"+ test_case+" "+password.get(K-1));
		}
	}
}